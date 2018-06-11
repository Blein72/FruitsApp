package com.example.fruitsapp.domain.interactor.fruitdetails

import com.example.fruitsapp.domain.model.Fruit
import io.reactivex.Observable

interface FruitDetailsInteractor {
    fun getFruitById(id: String): Observable<Fruit>
}