package com.mobiquity.mobproducts.di

import androidx.lifecycle.ViewModel
import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase
import com.mobiquity.mobproducts.presentation.viewmodel.BaseViewModel
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    fun productViewModel(useCase: ProductsUseCase): ViewModel = ProductsViewModel(useCase)
}


