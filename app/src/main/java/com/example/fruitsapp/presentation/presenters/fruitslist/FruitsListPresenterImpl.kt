package com.example.fruitsapp.presentation.presenters.fruitslist

import com.example.fruitsapp.domain.interactor.fruitslist.FruitsListInteractor
import com.example.fruitsapp.domain.model.Fruit
import com.example.fruitsapp.presentation.ui.fragments.fruitlist.FruitsListFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FruitsListPresenterImpl @Inject
constructor(private val view: FruitsListFragmentView,
            private val interactor: FruitsListInteractor) : FruitsListPresenter {

    private fun onRequestSuccess(data: List<Fruit>) {
        view.hideProgress()
        view.showContent()
        view.setFruitListData(data)
    }

    private fun onError() {
        view.hideProgress()
        view.showErrorDialog()
    }

    override fun sendFruitListRequest() {
        interactor.getFruitList()
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

    private fun sendRequestWithProggress() {
        view.showProgress()
        view.hideContent()
    }

    override fun goToFruitDetails(id: Long) {
        view.showFruitDetails(id)
    }

    override fun goToAddFruit() {
        view.showAddFruitFragment()
    }

    override fun getFruitslist() {
        sendFruitListRequest()
    }
}