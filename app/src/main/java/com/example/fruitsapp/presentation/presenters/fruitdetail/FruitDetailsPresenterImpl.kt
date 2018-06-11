package com.example.fruitsapp.presentation.presenters.fruitdetail

import com.example.fruitsapp.domain.interactor.fruitdetails.FruitDetailsInteractor
import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.FruitDetailsFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FruitDetailsPresenterImpl(private val id: String,
                                private val view: FruitDetailsFragmentView,
                                private val interactor: FruitDetailsInteractor): FruitDetailsPresenter {

    init {
        view.showProgress()
        view.hideContent()
        sendFruitDetailsRequest()
    }

    private fun onRequestSuccess(data: Fruit) {
        view.hideProgress()
        view.showContent()
        view.setFruitDetails(data)
    }

    private fun sendFruitDetailsRequest() {
        interactor.getFruitById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            onRequestSuccess(it)
                        },
                        onError = {
                            onError()
                        }
                )
    }

    private fun onError() {

    }
}