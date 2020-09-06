package com.mobiquity.mobproducts.mockedApi

import com.mobiquity.mobproducts.ProductCategoriesTest
import com.mobiquity.mobproducts.ProductsApplicaton
import com.mobiquity.mobproducts.di.*
import com.mobiquity.mobproducts.helper.TestApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ViewModelModule::class,
        InjectorBindingModules::class,
        AndroidInjectionModule::class,
        FragmentInjectorsModule::class,
        FakeRepositoryModule::class,
        NetworkModule::class]
)

interface TestApplicationComponent : AndroidInjector<ProductsApplicaton> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ProductsApplicaton>() {
        abstract fun appModule(appModule: FakeRepositoryModule): Builder
    }

}