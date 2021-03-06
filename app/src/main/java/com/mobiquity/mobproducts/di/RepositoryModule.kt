package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.data.ProductRepositoryImpl
import com.mobiquity.mobproducts.data.ProductsService
import com.mobiquity.mobproducts.domain.ProductsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideProductsRepository(productService: ProductsService): ProductsRepository =
        ProductRepositoryImpl(productService)
}