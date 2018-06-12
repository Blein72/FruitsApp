package com.example.fruitsapp.presentation.ui.activities.main

import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.fruitsapp.R
import com.example.fruitsapp.domain.app.App
import com.example.fruitsapp.domain.model.FragmentArgument
import com.example.fruitsapp.presentation.ui.activities.main.di.DaggerMainScreenComponent
import com.example.fruitsapp.presentation.ui.activities.main.di.MainScreenComponent
import com.example.fruitsapp.presentation.ui.activities.main.di.MainScreenModule
import com.example.fruitsapp.presentation.ui.fragments.addfruit.AddFruitFragment
import com.example.fruitsapp.presentation.ui.fragments.addfruit.AddFruitFragmentInjector
import com.example.fruitsapp.presentation.ui.fragments.addfruit.di.AddFruitModule
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.FruitDetailsFragment
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.FruitDetailsFragmentInjector
import com.example.fruitsapp.presentation.ui.fragments.fruitdetail.di.FruitDetailsModule
import com.example.fruitsapp.presentation.ui.fragments.fruitlist.FruitsListFragment
import com.example.fruitsapp.presentation.ui.fragments.fruitlist.FruitsListFragmentInjector
import com.example.fruitsapp.presentation.ui.fragments.fruitlist.di.FruitsListModule

class MainScreen : AppCompatActivity(),
        FruitsListFragmentInjector,
        FruitDetailsFragmentInjector,
        AddFruitFragmentInjector,
        MainScreenView{

    lateinit var component: MainScreenComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupComponent()
        setContentView(R.layout.activity_main)

        setInitialFragment()
    }

    private fun setInitialFragment() {
        setNextFragment(MainScreenTypesRouter.Companion.Types.FRUIT_LIST_SCREEN)
    }

    fun setupComponent() {
        component = DaggerMainScreenComponent.builder()
                .appComponent((application as App).getAppComponent())
                .mainScreenModule(MainScreenModule(this,
                        R.id.content))
                .build()
        component.inject(this)
    }

    override fun inject(view: FruitsListFragment) {
        component.plus(FruitsListModule(view)).inject(view)
    }

    override fun inject(view: FruitDetailsFragment) {
        component.plus(FruitDetailsModule(view)).inject(view)
    }

    override fun inject(view: AddFruitFragment) {
        component.plus(AddFruitModule(view)).inject(view)
    }

    override fun setNextFragment(type: MainScreenTypesRouter.Companion.Types, data: FragmentArgument?) {
        val fragmentManager: FragmentManager = fragmentManager
        val fragment = MainScreenTypesRouter.getFragment(type,data)
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment, type.tag)
                .commit()
    }
}
