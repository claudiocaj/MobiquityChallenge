package com.mobiquity.mobproducts.data

import com.mobiquity.mobproducts.BuildConfig
import com.mobiquity.mobproducts.domain.ProductsRepository
import com.mobiquity.mobproducts.domain.entities.Category
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val service: ProductsService) :
    ProductsRepository {

    override fun getProducts(): Observable<List<Category>> {
        return service.getProducts(BuildConfig.API_URL)
    }
}