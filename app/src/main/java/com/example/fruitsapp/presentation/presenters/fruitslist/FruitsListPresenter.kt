package com.example.fruitsapp.presentation.presenters.fruitslist

interface FruitsListPresenter {

    fun getFruitslist()
    fun goToFruitDetails(id: Long)
    fun goToAddFruit()
    fun sendFruitListRequest()
}