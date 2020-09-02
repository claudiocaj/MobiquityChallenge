package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModules {

    @Provides
    @Singleton
    fun provideProductViewModel(productsUseCase: ProductsUseCase): ProductsViewModel =
        ProductsViewModel(productsUseCase)

}