package com.example.fruitsapp.domain.repository

import com.example.fruitsapp.domain.repository.fruits.FruitsRepository
import com.example.fruitsapp.domain.repository.fruits.FruitsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(repository: FruitsRepositoryImpl):  FruitsRepository {
        return repository
    }
}