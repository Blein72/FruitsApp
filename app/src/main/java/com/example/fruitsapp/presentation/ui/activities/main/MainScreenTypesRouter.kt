package com.example.fruitsapp.presentation.ui.activities.main

import android.app.Fragment
import com.example.fruitsapp.domain.model.FragmentArgument
import com.example.fruitsapp.presentation.ui.fragments.addfruit.AddFruitFragment
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.FruitDetailsFragment
import com.example.fruitsapp.presentation.ui.fragments.fruitlist.FruitsListFragment

class MainScreenTypesRouter {

    companion object {
        enum class Types(val tag: String) {
            FRUIT_LIST_SCREEN("FRUIT_LIST_SCREEN"),
            FRUIT_DETAILS_SCREEN("FRUIT_LIST_SCREEN"),
            ADD_FRUIT_SCREEN("ADD_FRUIT_SCREEN")
        }

        public fun getFragment(type: Types, data: FragmentArgument? = null): Fragment {
            return when(type) {
                Types.FRUIT_LIST_SCREEN -> FruitsListFragment()
                Types.ADD_FRUIT_SCREEN -> AddFruitFragment()
                Types.FRUIT_DETAILS_SCREEN -> FruitDetailsFragment.newInstance(data)
            }
        }
    }

}