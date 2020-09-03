package com.mobiquity.mobproducts.data

import com.mobiquity.mobproducts.BuildConfig
import com.mobiquity.mobproducts.domain.ProductsRepository
import com.mobiquity.mobproducts.domain.entities.Category
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit

class ProductRepositoryImpl(val retrofit: Retrofit) : ProductsRepository {

    override fun getProducts(): Observable<List<Category>> {
        val productsService = retrofit.create(ProductsService::class.java)
        return productsService.getProducts(BuildConfig.API_URL)
    }
}