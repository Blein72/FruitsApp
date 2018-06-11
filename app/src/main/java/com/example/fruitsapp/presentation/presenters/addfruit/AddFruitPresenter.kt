package com.example.fruitsapp.presentation.presenters.addfruit

import com.example.fruitsapp.domain.model.NewFruitDto

interface AddFruitPresenter {
    fun sendNewFruit(data: NewFruitDto)
}