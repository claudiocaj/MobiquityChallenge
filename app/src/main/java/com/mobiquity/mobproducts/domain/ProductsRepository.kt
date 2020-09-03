package com.mobiquity.mobproducts.domain

import com.mobiquity.mobproducts.domain.entities.Category
import io.reactivex.rxjava3.core.Observable

interface ProductsRepository {

    fun getProducts(): Observable<List<Category>>
}