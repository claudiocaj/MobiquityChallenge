package com.mobiquity.mobproducts.helper

import androidx.test.platform.app.InstrumentationRegistry
import com.mobiquity.mobproducts.ProductsApplicaton
import com.mobiquity.mobproducts.di.DaggerTestApplicationComponent
import com.mobiquity.mobproducts.di.FakeRepositoryModule

class TestInjector(private val testApplicationModule: FakeRepositoryModule) {

    fun inject() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as ProductsApplicaton

        DaggerTestApplicationComponent
            .builder()
            .appModule(testApplicationModule)
            .create(app)
            .inject(app)
    }
}