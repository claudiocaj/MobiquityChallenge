package com.mobiquity.mobproducts

import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mobiquity.mobproducts.data.ProductRepositoryImpl
import com.mobiquity.mobproducts.data.ProductsService
import com.mobiquity.mobproducts.domain.ProductsRepository
import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase
import com.mobiquity.mobproducts.mockedApi.DaggerMockedComponent
import com.mobiquity.mobproducts.mockedApi.MockedComponent
import com.mobiquity.mobproducts.presentation.ui.MainActivity
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class ProductCategoriesTest {

    /*@Inject
    lateinit var useCase: ProductsUseCase*/

    @get:Rule
    val testRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    private lateinit var testAppComponent: MockedComponent

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val app =
            InstrumentationRegistry.getTargetContext().applicationContext as ProductsApplicaton

        testAppComponent = DaggerMockedComponent.builder().build()

        app.appComponent = testAppComponent
        testAppComponent.inject(this)

    }

    @Test
    fun getProducts() {

     /*   Mockito.`when`(useCase.getProducts())
            .thenReturn(Observable.just(DummyConstantsTest.DUMMY_CATEGORIES))*/
        testRule.launchActivity(null)

    }
}