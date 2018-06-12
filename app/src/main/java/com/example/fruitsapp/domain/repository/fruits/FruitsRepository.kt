package com.example.fruitsapp.domain.repository.fruits

import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.domain.model.NewFruitDto
import io.reactivex.Observable

interface FruitsRepository {

    fun getFruitsList(): Observable<List<Fruit>>

    fun getFruitById(id: String): Observable<Fruit>

    fun addNewFruit(data: NewFruitDto): Observable<Fruit>

    fun saveFruitList(data: List<Fruit>)

    fun getCachedFruitlist(): List<Fruit>?
}