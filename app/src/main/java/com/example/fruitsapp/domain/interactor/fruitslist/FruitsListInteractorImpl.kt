package com.example.fruitsapp.domain.interactor.fruitslist

import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.domain.repository.fruits.FruitsRepositoryImpl
import io.reactivex.Observable
import javax.inject.Inject

class FruitsListInteractorImpl @Inject constructor
(private val repository: FruitsRepositoryImpl): FruitsListInteractor {

    override fun getFruitList(): Observable<List<Fruit>> {
       return repository.getFruitsList()
    }

    override fun saveFruitList(data: List<Fruit>) {
        repository.saveFruitList(data)
    }

    override fun getCachedFruitlist(): List<Fruit>? {
       return repository.getCachedFruitlist()
    }


}