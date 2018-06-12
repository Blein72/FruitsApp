package com.example.fruitsapp.presentation.ui.fragments.fruitdetail

import com.example.fruitsapp.domain.model.Fruit

interface FruitDetailsFragmentView {

    fun setFruitDetails(data: Fruit)
    fun showProgress()
    fun hideProgress()
    fun showContent()
    fun hideContent()

    fun showErrorDialog()
}