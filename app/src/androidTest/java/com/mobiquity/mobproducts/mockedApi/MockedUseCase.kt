package com.mobiquity.mobproducts.mockedApi

import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class MockedUseCase {

    @Provides
    fun provideUseCase(): ProductsUseCase {
        return Mockito.mock(ProductsUseCase::class.java)
    }
}