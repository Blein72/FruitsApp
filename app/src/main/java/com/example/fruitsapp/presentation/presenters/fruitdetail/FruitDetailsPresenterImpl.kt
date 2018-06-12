package com.example.fruitsapp.presentation.presenters.fruitdetail

import com.example.fruitsapp.domain.interactor.fruitdetails.FruitDetailsInteractor
import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.FruitDetailsFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FruitDetailsPresenterImpl @Inject constructor(
        private val view: FruitDetailsFragmentView,
        private val interactor: FruitDetailsInteractor) : FruitDetailsPresenter {

    var id: Long = 0

    private fun onRequestSuccess(data: Fruit) {
        view.hideProgress()
        view.showContent()
        view.setFruitDetails(data)
    }

    override fun sendFruitDetailsRequest() {
        view.showProgress()
        view.hideContent()
        interactor.getFruitById(id.toString())
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

    override fun setFruitId(id: Long) {
        this.id = id
    }

    private fun onError() {
        view.showErrorDialog()
    }
}