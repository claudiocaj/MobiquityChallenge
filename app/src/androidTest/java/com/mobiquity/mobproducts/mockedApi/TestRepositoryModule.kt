package com.mobiquity.mobproducts.mockedApi

import com.mobiquity.mobproducts.data.ProductRepositoryImpl
import com.mobiquity.mobproducts.domain.ProductsRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
class TestRepositoryModule {

    @Provides
    @Singleton
    fun provideMockRepository(): ProductsRepository =
        Mockito.mock(ProductRepositoryImpl::class.java)
}