package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.presentation.ui.DetailFragment
import com.mobiquity.mobproducts.presentation.ui.MainActivity
import com.mobiquity.mobproducts.presentation.ui.ProductsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        RepositoryModule::class,
        NetworkModule::class]
)

interface AppComponent {
    fun inject(target: MainActivity)
    fun inject(target: ProductsFragment)
    fun inject(target: DetailFragment)
}