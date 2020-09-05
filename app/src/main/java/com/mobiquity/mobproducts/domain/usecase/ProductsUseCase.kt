package com.mobiquity.mobproducts.domain.usecase

import com.mobiquity.mobproducts.annotations.DebugOpenClass
import com.mobiquity.mobproducts.domain.ProductsRepository
import com.mobiquity.mobproducts.domain.entities.Category
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@DebugOpenClass
class ProductsUseCase @Inject constructor(private val repository: ProductsRepository) {

    fun getProducts(): Observable<List<Category>> = repository.getProducts()
}