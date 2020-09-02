package com.mobiquity.mobproducts

import android.app.Application
import com.mobiquity.mobproducts.di.AppComponent
import com.mobiquity.mobproducts.di.AppModule
import com.mobiquity.mobproducts.di.DaggerAppComponent

class ProductsApplicaton : Application() {

    lateinit var appComponent: AppComponent

    private fun initDagger(): AppComponent =
        DaggerAppComponent.builder().appModule(AppModule(this)).build()

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger()
    }
}