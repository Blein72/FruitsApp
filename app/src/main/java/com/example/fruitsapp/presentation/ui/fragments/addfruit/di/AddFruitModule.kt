package com.example.fruitsapp.presentation.ui.fragments.addfruit.di

import com.example.fruitsapp.domain.di.FragmentScope
import com.example.fruitsapp.domain.interactor.fruitdetails.FruitDetailsInteractor
import com.example.fruitsapp.domain.interactor.fruitdetails.FruitDetailsInteractorImpl
import com.example.fruitsapp.presentation.presenters.addfruit.AddFruitPresenter
import com.example.fruitsapp.presentation.presenters.addfruit.AddFruitPresenterImpl
import com.example.fruitsapp.presentation.ui.fragments.addfruit.AddFruitFragmentView
import dagger.Module
import dagger.Provides

@Module
class AddFruitModule(private val view: AddFruitFragmentView) {

    @Provides
    @FragmentScope
    fun provideView(): AddFruitFragmentView {
        return this.view
    }

    @Provides
    @FragmentScope
    fun providePresenter(presenter: AddFruitPresenterImpl): AddFruitPresenter {
        return presenter
    }

    @Provides
    @FragmentScope
    fun provideInteractor(interactor: FruitDetailsInteractorImpl): FruitDetailsInteractor {
        return interactor
    }
}