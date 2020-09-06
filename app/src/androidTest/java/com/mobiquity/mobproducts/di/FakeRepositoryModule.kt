package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.domain.ProductsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeRepositoryModule(val userRepository: ProductsRepository = FakeRepository()) {

    @Provides
    @Singleton
    fun provideMockRepository(): ProductsRepository = userRepository
}