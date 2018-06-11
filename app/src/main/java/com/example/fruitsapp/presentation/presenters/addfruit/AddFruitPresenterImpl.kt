package com.example.fruitsapp.presentation.presenters.addfruit

import com.example.fruitsapp.domain.interactor.addfruit.AddFruitInteractor
import com.example.fruitsapp.domain.model.NewFruitDto
import com.example.fruitsapp.presentation.ui.fragments.addfruit.AddFruitFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AddFruitPresenterImpl(
        private val view: AddFruitFragmentView,
        private val interactor: AddFruitInteractor) : AddFruitPresenter {

    override fun sendNewFruit(data: NewFruitDto) {

        interactor.addNewFruit(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribeBy(
                        onComplete = {
                            view.dataSendSuccessful()
                        },
                        onError = {
                            view.errorSendingData()
                        })
    }
}