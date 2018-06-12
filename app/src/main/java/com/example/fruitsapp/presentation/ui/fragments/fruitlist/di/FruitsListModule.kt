package com.example.fruitsapp.presentation.ui.fragments.fruitlist.di

import com.example.fruitsapp.domain.di.FragmentScope
import com.example.fruitsapp.domain.interactor.fruitslist.FruitsListInteractor
import com.example.fruitsapp.domain.interactor.fruitslist.FruitsListInteractorImpl
import com.example.fruitsapp.presentation.presenters.fruitslist.FruitsListPresenter
import com.example.fruitsapp.presentation.presenters.fruitslist.FruitsListPresenterImpl
import com.example.fruitsapp.presentation.ui.fragments.fruitlist.FruitsListFragmentView
import dagger.Module
import dagger.Provides

@Module
class FruitsListModule(private val view: FruitsListFragmentView) {

    @Provides
    @FragmentScope
    fun provideView(): FruitsListFragmentView {
        return this.view
    }

    @Provides
    @FragmentScope
    fun provideInteractor(interactor: FruitsListInteractorImpl): FruitsListInteractor {
        return interactor
    }

    @Provides
    @FragmentScope
    fun providePresenter(presenter: FruitsListPresenterImpl): FruitsListPresenter {
        return presenter
    }


}