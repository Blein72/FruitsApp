package com.example.fruitsapp.domain.repository.fruits

import com.example.fruitsapp.domain.apiinterfaces.FruitsApiInterface
import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.domain.model.FruitListObject
import com.example.fruitsapp.domain.model.NewFruitDto
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmList
import io.realm.kotlin.where
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class FruitsRepositoryImpl @Inject constructor() : FruitsRepository {
    private val retrofit: Retrofit
    private val fruitInterface: FruitsApiInterface

    private val realm: Realm

    init {
        retrofit = Retrofit.Builder()
                .baseUrl("https://devmatic.github.io/fruits-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        fruitInterface = retrofit.create(FruitsApiInterface::class.java)

        realm = Realm.getDefaultInstance()
    }

    override fun getFruitsList(): Observable<List<Fruit>> {
        return fruitInterface.getFruitsList().map { it.map { it.createModel() } }

        //for testing without server
        //return testListData()
    }

    override fun getFruitById(id: String): Observable<Fruit> {
        return fruitInterface.getFruitById(id).map { it.createModel() }
        
        //for testing without server
        // return testFruit()
    }

    override fun addNewFruit(data: NewFruitDto): Observable<Fruit> {
        return fruitInterface.addFruit(
                name = data.name,
                weight = data.weight,
                delicious = data.delicious,
                color = data.color).map { it.createModel() }
    }

    override fun saveFruitList(data: List<Fruit>) {
        realm.executeTransaction { realm ->
            val realmList = RealmList<Fruit>()
            realmList.addAll(data)
            realm.copyToRealmOrUpdate(FruitListObject(100, realmList))
        }
    }

    override fun getCachedFruitlist(): List<Fruit>? {
        val fruitList = realm.where<FruitListObject>().findFirst()
        return if (fruitList != null) {
            createFruitListFromRealmObject(fruitList)
        } else {
            emptyList()
        }
    }

    private fun createFruitListFromRealmObject(data: FruitListObject): List<Fruit> {
        val listData = data.list
        return if (listData != null) {
            val resultList: MutableList<Fruit> = mutableListOf()

            for (i in 0..listData.size - 1) {
                val item = listData[i]
                if (item != null) {
                    resultList.add(Fruit(
                            id = item.id,
                            name = item.name,
                            color = item.color,
                            delicious = item.delicious,
                            weight = item.weight,
                            created_at = item.created_at,
                            updated_at = item.updated_at))
                }
            }
            return resultList
        } else {
            emptyList()
        }
    }

    //data for testing
    private fun testListData(): Observable<List<Fruit>> {
        val fruit1 = Fruit(111, "aaaaa", "color1", 1, true, "100", "101")
        val fruit2 = Fruit(222, "bbbbb", "color2", 1, true, "110", "111")
        val fruit3 = Fruit(333, "ccccc", "color3", 1, true, "120", "121")
        val fruits: MutableList<Fruit> = mutableListOf()
        fruits.add(fruit1)
        fruits.add(fruit2)
        fruits.add(fruit3)
        return Observable.just(fruits)
    }

    //data for testing
    private fun testFruit(): Observable<Fruit> {
        return Observable.just(Fruit(111, "aaaaa", "color1", 1, true, "100", "101"))
    }
}