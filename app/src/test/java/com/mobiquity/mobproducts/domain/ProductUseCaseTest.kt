package com.mobiquity.mobproducts.domain

import com.mobiquity.mobproducts.DummyConstants.DUMMY_CATEGORIES
import com.mobiquity.mobproducts.domain.entities.Category
import com.mobiquity.mobproducts.domain.entities.Price
import com.mobiquity.mobproducts.domain.entities.Product
import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test

class ProductUseCaseTest {

    @MockK
    private lateinit var productsRepository: ProductsRepository

    private lateinit var productsUseCase: ProductsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        productsUseCase = ProductsUseCase((productsRepository))
    }

    @Test
    fun shouldBringDataWhenHasData() {
        //Prepare
        every { productsRepository.getProducts() } returns Observable.just(DUMMY_CATEGORIES)

        //Act
        val testObserver = productsUseCase.getProducts().test()

        //Assert
        verify { productsRepository.getProducts() }
        testObserver.assertComplete()
        testObserver.assertValue(DUMMY_CATEGORIES)
    }

    @Test
    fun shouldNotBringDataWhenHasNoData() {
        //Prepare
        every { productsRepository.getProducts() } returns Observable.just(listOf<Category>())

        //Act
        val testObserver = productsUseCase.getProducts().test()

        //Assert
        verify { productsRepository.getProducts() }
        testObserver.assertComplete()
        testObserver.assertValue(listOf<Category>())
    }


}