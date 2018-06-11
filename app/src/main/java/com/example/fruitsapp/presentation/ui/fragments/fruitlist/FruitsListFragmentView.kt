package com.example.fruitsapp.presentation.ui.fragments.fruitlist

import com.example.fruitsapp.domain.model.Fruit

interface FruitsListFragmentView {

    fun setFruitListData(data: List<Fruit>)
    fun showFruitDetails(id: Long)
    fun showAddFruitFragment()
    fun showProgress()
    fun hideProgress()
    fun showContent()
    fun hideContent()

}