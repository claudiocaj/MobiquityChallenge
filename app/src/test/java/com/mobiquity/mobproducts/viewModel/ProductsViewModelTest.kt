package com.mobiquity.mobproducts.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.getOrAwaitValue
import com.mobiquity.mobproducts.DummyConstants
import com.mobiquity.mobproducts.helper.RxImmediateSchedulerRule
import com.mobiquity.mobproducts.domain.entities.Category
import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ProductsViewModelTest {

    @Rule
    @JvmField
    val rxImmediateSchedulerRule =
        RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockedProductuseCase: ProductsUseCase

    private lateinit var productViewModel: ProductsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        productViewModel = ProductsViewModel(mockedProductuseCase)
    }

    @Test
    fun testGetProducts_whenSuccess() {
        //Prepare
        every { mockedProductuseCase.getProducts() } returns Observable.just(DummyConstants.DUMMY_CATEGORIES)

        //Act
        productViewModel.fetchProducts()
        val result: Result<List<Category>> = productViewModel.getProducts().getOrAwaitValue()

        //Assert
        verify { mockedProductuseCase.getProducts() }
        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(DummyConstants.DUMMY_CATEGORIES, result.getOrThrow())
    }

    @Test
    fun testGetProducts_whenError() {
        //Prepare
        every { mockedProductuseCase.getProducts() } returns Observable.error(IOException())

        //Act
        productViewModel.fetchProducts()
        val result: Result<List<Category>> = productViewModel.getProducts().getOrAwaitValue()

        //Assert
        verify { mockedProductuseCase.getProducts() }
        Assert.assertTrue(result.isFailure)
        Assert.assertTrue(result.exceptionOrNull() is IOException)
    }

    @Test
    fun testGetProducts_whenLoading() {
        //Prepare
        every { mockedProductuseCase.getProducts() } returns Observable.create { }

        //Act
        productViewModel.fetchProducts()

        //Assert
        verify { mockedProductuseCase.getProducts() }
        Assert.assertEquals(productViewModel.isLoadingMutable.value, true)
    }

    @Test
    fun testLoadingStatus_whenSuccess() {
        //Prepare
        every { mockedProductuseCase.getProducts() } returns Observable.just(DummyConstants.DUMMY_CATEGORIES)

        //Act
        productViewModel.fetchProducts()

        //Assert
        verify { mockedProductuseCase.getProducts() }
        Assert.assertEquals(productViewModel.isLoadingMutable.value, false)
    }
}