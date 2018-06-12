package com.example.fruitsapp.domain.repository.fruits

import com.example.fruitsapp.domain.apiinterfaces.FruitsApiInterface
import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.domain.model.NewFruitDto
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class FruitsRepositoryImpl @Inject constructor(): FruitsRepository {
    private val retrofit: Retrofit
    private val fruitInterface: FruitsApiInterface
    init {
        retrofit = Retrofit.Builder()
                .baseUrl("https://devmatic.github.io/fruits-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

       fruitInterface = retrofit.create(FruitsApiInterface::class.java)
    }

    override fun getFruitsList(): Observable<List<Fruit>> {
       return fruitInterface.getFruitsList()
    }

    override fun getFruitById(id: String): Observable<Fruit> {
        return fruitInterface.getFruitById(id)
    }

    override fun addNewFruit(data: NewFruitDto): Observable<Fruit> {
       return fruitInterface.addFruit(
                name = data.name,
                weight = data.weight,
                delicious = data.delicious,
                color = data.color)
    }
}