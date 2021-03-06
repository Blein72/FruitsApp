package com.example.fruitsapp.domain.interactor.fruitslist

import com.example.fruitsapp.domain.model.Fruit
import io.reactivex.Observable

interface FruitsListInteractor {
    fun getFruitList(): Observable<List<Fruit>>

    fun saveFruitList(data: List<Fruit>)

    fun getCachedFruitlist(): List<Fruit>?
}