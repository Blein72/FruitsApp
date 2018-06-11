package com.example.fruitsapp.presentation.ui.fragments.fruitdetail.di

import com.example.fruitsapp.domain.di.FragmentScope
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.FruitDetailsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = arrayOf(FruitDetailsModule::class))
interface FruitDetailsComponent {
    fun inject(fragment: FruitDetailsFragment)
}