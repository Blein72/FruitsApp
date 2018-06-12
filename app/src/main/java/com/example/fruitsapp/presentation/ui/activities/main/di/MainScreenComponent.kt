package com.example.fruitsapp.presentation.ui.activities.main.di

import com.example.fruitsapp.domain.app.di.AppComponent
import com.example.fruitsapp.domain.di.ActivityScope
import com.example.fruitsapp.presentation.ui.activities.main.MainScreen
import com.example.fruitsapp.presentation.ui.fragments.addfruit.di.AddFruitComponent
import com.example.fruitsapp.presentation.ui.fragments.addfruit.di.AddFruitModule
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.di.FruitDetailsComponent
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.di.FruitDetailsModule
import com.example.fruitsapp.presentation.ui.fragments.fruitlist.di.FruitsListComponent
import com.example.fruitsapp.presentation.ui.fragments.fruitlist.di.FruitsListModule
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainScreenModule::class))
interface MainScreenComponent {

    fun inject(mainScreen: MainScreen)

    fun plus(screensModule: FruitsListModule): FruitsListComponent
    fun plus(screenModule: FruitDetailsModule): FruitDetailsComponent
    fun plus(screenModule: AddFruitModule): AddFruitComponent
}