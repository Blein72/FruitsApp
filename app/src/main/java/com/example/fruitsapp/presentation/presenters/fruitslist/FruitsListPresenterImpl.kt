package com.example.fruitsapp.presentation.presenters.fruitslist

import android.util.Log
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
        cacheList(data)
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
                            Log.e("OBSERVABLE ERROR!!!!!", it.toString())
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

    override fun getCachedListAndSendRequest() {
        showCachedList()
        sendFruitListRequest()
    }

    private fun cacheList(data: List<Fruit>) {
        interactor.saveFruitList(data)
    }

    private fun showCachedList() {
        val data = interactor.getCachedFruitlist()
        if (data != null) {
            view.hideProgress()
            view.showContent()
            view.setFruitListData(data)
        }
    }
}