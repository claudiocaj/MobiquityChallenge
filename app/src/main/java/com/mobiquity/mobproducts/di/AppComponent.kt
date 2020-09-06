package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.ProductsApplicaton
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

interface AppComponent {
    fun inject(application: ProductsApplicaton)
    
}