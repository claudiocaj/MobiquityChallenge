package com.mobiquity.mobproducts.di

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector

@Module
@Suppress("unused")
abstract class InjectorBindingModules {
    @Binds
    abstract fun activityInjector(activityInjector: DispatchingAndroidInjector<Activity>): AndroidInjector<Activity>

    @Binds
    abstract fun fragmentInjector(fragmentInjector: DispatchingAndroidInjector<Fragment>): AndroidInjector<Fragment>
}