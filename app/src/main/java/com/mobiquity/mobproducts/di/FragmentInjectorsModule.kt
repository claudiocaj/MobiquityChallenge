package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.presentation.ui.DetailFragment
import com.mobiquity.mobproducts.presentation.ui.ProductsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class FragmentInjectorsModule {

    @ContributesAndroidInjector
    abstract fun productsFragment(): ProductsFragment

    @ContributesAndroidInjector
    abstract fun productDetailFragment(): DetailFragment
}