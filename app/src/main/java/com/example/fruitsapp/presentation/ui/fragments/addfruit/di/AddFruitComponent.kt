package com.example.fruitsapp.presentation.ui.fragments.addfruit.di

import com.example.fruitsapp.domain.di.FragmentScope
import com.example.fruitsapp.presentation.ui.fragments.addfruit.AddFruitFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = arrayOf(AddFruitModule::class))
interface AddFruitComponent {
    fun inject(fragment: AddFruitFragment)
}