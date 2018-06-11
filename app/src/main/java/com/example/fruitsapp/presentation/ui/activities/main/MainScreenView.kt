package com.example.fruitsapp.presentation.ui.activities.main

import com.example.fruitsapp.domain.model.FragmentArgument

interface MainScreenView {
    fun setNextFragment(type: MainScreenTypesRouter.Companion.Types, data: FragmentArgument? = null)
}