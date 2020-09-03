package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.data.ProductRepositoryImpl
import com.mobiquity.mobproducts.domain.ProductsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(retrofit: Retrofit): ProductsRepository =
        ProductRepositoryImpl(retrofit)
}