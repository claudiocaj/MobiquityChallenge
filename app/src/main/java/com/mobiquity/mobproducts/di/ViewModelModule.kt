package com.mobiquity.mobproducts.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase
import com.mobiquity.mobproducts.presentation.ui.MainActivity
import com.mobiquity.mobproducts.presentation.viewmodel.BaseViewModel
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule.ProvideViewModel::class
    ]
)
abstract class ViewModelModule {

    @Module
    class InjectViewModel() {

        @Provides
        fun productsViewModel(
            factory: ViewModelProvider.Factory,
            target: MainActivity
        ): ProductsViewModel =
            ViewModelProviders.of(target, factory).get(ProductsViewModel::class.java)
    }


    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(BaseViewModel::class)
        fun productViewModel(useCase: ProductsUseCase): ViewModel = ProductsViewModel(useCase)
    }


}