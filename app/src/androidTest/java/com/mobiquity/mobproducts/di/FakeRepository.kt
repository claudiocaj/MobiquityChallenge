package com.mobiquity.mobproducts.di

import com.mobiquity.mobproducts.helper.DummyConstants
import com.mobiquity.mobproducts.domain.ProductsRepository
import com.mobiquity.mobproducts.domain.entities.Category
import io.reactivex.rxjava3.core.Observable

class FakeRepository : ProductsRepository {
    override fun getProducts(): Observable<List<Category>> {
        return Observable.just(DummyConstants.DUMMY_CATEGORIES)
    }
}