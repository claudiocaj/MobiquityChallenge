package com.mobiquity.mobproducts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobiquity.mobproducts.domain.entities.Category
import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase

class ProductsViewModel(private val useCase: ProductsUseCase) : BaseViewModel() {
    private val productsLiveData = MutableLiveData<Result<List<Category>>>()

    fun getProducts(): LiveData<Result<List<Category>>> {
        return productsLiveData
    }

    fun fetchProducts(): Boolean {
        return useCase.getProducts()
    }

}