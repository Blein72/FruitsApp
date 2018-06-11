package com.example.fruitsapp.presentation.ui.fragments.addfruit

interface AddFruitFragmentView {
    fun sendFruitData()
    fun showProgress()
    fun hideProgress()
    fun dataSendSuccessful()
    fun errorSendingData()
}