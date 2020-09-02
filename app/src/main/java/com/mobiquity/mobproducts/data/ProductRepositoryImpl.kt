package com.mobiquity.mobproducts.data

import com.mobiquity.mobproducts.domain.ProductsRepository

class ProductRepositoryImpl : ProductsRepository {

    override fun getProducts(): Boolean {
        return true
    }
}