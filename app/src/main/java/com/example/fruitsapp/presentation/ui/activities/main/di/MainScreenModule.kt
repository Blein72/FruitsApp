package com.example.fruitsapp.presentation.ui.activities.main.di

import com.example.fruitsapp.domain.di.ActivityScope
import com.example.fruitsapp.presentation.ui.activities.main.MainScreenView
import dagger.Module
import dagger.Provides

@Module
class MainScreenModule(private val mainView: MainScreenView, containerId: Int) {

    @Provides
    @ActivityScope
    fun provideView(): MainScreenView {
        return this.mainView
    }
}