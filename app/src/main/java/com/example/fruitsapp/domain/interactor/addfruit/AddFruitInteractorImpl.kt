package com.example.fruitsapp.domain.interactor.addfruit

import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.domain.model.NewFruitDto
import com.example.fruitsapp.domain.repository.fruits.FruitsRepository
import io.reactivex.Observable
import javax.inject.Inject

class AddFruitInteractorImpl @Inject constructor(
        private val repository: FruitsRepository): AddFruitInteractor {

    override fun addNewFruit(data: NewFruitDto): Observable<Fruit> {
        return repository.addNewFruit(data)
    }
}