package com.example.fruitsapp.domain.interactor.addfruit

import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.domain.model.NewFruitDto
import io.reactivex.Observable

interface AddFruitInteractor {

    fun addNewFruit(data: NewFruitDto): Observable<Fruit>
}