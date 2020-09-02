package com.mobiquity.mobproducts.domain.usecase

import com.mobiquity.mobproducts.domain.ProductsRepository

class ProductsUseCase(private val repository: ProductsRepository) {

    fun getProducts(): Boolean = repository.getProducts()
}