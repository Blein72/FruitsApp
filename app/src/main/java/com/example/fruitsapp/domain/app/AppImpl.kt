package com.example.fruitsapp.domain.app

import android.app.Application
import com.example.fruitsapp.domain.app.di.AppComponent
import com.example.fruitsapp.domain.app.di.AppModule
import com.example.fruitsapp.domain.app.di.DaggerAppComponent
import com.example.fruitsapp.domain.repository.RepositoryModule

class AppImpl: Application(),App {

    override fun getAppComponent(): AppComponent = component

    private val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .repositoryModule(RepositoryModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}