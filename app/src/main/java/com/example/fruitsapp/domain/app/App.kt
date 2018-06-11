package com.example.fruitsapp.domain.app

import com.example.fruitsapp.domain.app.di.AppComponent

interface App {

    fun getAppComponent(): AppComponent
}
