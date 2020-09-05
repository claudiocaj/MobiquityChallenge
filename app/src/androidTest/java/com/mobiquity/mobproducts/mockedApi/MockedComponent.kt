package com.mobiquity.mobproducts.mockedApi

import com.mobiquity.mobproducts.ProductCategoriesTest
import com.mobiquity.mobproducts.ProductsApplicaton
import com.mobiquity.mobproducts.di.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ViewModelModule::class,
        InjectorBindingModules::class,
        AndroidInjectionModule::class,
        FragmentInjectorsModule::class,
        RepositoryModule::class,
        NetworkModule::class]
)

interface MockedComponent : AppComponent {
    fun inject(test: ProductCategoriesTest)
}