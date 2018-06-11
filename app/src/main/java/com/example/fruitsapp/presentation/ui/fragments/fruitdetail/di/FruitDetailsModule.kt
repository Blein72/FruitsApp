package com.example.fruitsapp.presentation.ui.fragments.fruitdetail.di

import com.example.fruitsapp.domain.di.FragmentScope
import com.example.fruitsapp.domain.interactor.fruitdetails.FruitDetailsInteractor
import com.example.fruitsapp.domain.interactor.fruitdetails.FruitDetailsInteractorImpl
import com.example.fruitsapp.presentation.presenters.fruitdetail.FruitDetailsPresenter
import com.example.fruitsapp.presentation.presenters.fruitdetail.FruitDetailsPresenterImpl
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.FruitDetailsFragmentView
import dagger.Module
import dagger.Provides

@Module
class FruitDetailsModule(private val view: FruitDetailsFragmentView) {

    @Provides
    @FragmentScope
    fun provideView(): FruitDetailsFragmentView {
        return this.view
    }

    @Provides
    @FragmentScope
    fun providePresenter(presenter: FruitDetailsPresenterImpl): FruitDetailsPresenter {
        return presenter
    }

    @Provides
    @FragmentScope
    fun provideInteractor(interactor: FruitDetailsInteractorImpl): FruitDetailsInteractor {
        return interactor
    }
}