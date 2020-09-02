package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.domain.ProductsRepository
import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideUseModel(repository: ProductsRepository): ProductsUseCase =
        ProductsUseCase(repository)

}