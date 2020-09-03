package com.mobiquity.mobproducts.data

import com.mobiquity.mobproducts.BuildConfig
import com.mobiquity.mobproducts.domain.entities.Category
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductsService {
    @GET
    fun getProducts(@Url stringUrl: String): Observable<List<Category>>
}