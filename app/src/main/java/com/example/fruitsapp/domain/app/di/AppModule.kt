package com.example.fruitsapp.domain.app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.fruitsapp.domain.app.AppImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(app: AppImpl) {
    private val app: Application

    init {
        this.app = app
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext


    @Provides
    @Singleton
    fun providesSharedPreferences(): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(app)
}