package com.example.fruitsapp.domain.interactor.fruitdetails

import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.domain.repository.fruits.FruitsRepository
import io.reactivex.Observable
import javax.inject.Inject

class FruitDetailsInteractorImpl@Inject
constructor(private val repository: FruitsRepository): FruitDetailsInteractor {

    override fun getFruitById(id: String): Observable<Fruit> {
        return repository.getFruitById(id)
    }
}