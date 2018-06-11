package com.example.fruitsapp.presentation.ui.fragments.fruitlist.di

import com.example.fruitsapp.domain.di.FragmentScope
import com.example.fruitsapp.presentation.ui.fragments.fruitlist.FruitsListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = arrayOf(FruitsListModule::class))
interface FruitsListComponent {
    fun inject(fragment: FruitsListFragment)
}