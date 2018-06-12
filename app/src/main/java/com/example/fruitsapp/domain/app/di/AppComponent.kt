package com.example.fruitsapp.domain.app.di

import com.example.fruitsapp.domain.repository.RepositoryModule
import com.example.fruitsapp.domain.repository.fruits.FruitsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RepositoryModule::class))
interface AppComponent {

    fun repository(): FruitsRepository
}