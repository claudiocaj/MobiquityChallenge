package com.mobiquity.mobproducts.mockedApi

import com.mobiquity.mobproducts.DummyConstantsTest
import com.mobiquity.mobproducts.domain.ProductsRepository
import com.mobiquity.mobproducts.domain.entities.Category
import io.reactivex.rxjava3.core.Observable

class FakeRepository : ProductsRepository {
    override fun getProducts(): Observable<List<Category>> {
        return Observable.just(DummyConstantsTest.DUMMY_CATEGORIES)
    }
}