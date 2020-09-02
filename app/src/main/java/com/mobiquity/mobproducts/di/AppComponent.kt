package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.presentation.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModules::class]
)
interface AppComponent {
    fun inject(target: MainActivity)
}