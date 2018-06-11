package com.example.fruitsapp.domain.repository

import com.example.fruitsapp.domain.repository.fruits.FruitsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(): FruitsRepositoryImpl = FruitsRepositoryImpl()
}