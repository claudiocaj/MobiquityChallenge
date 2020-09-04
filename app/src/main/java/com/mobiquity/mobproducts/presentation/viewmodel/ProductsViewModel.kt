package com.mobiquity.mobproducts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobiquity.mobproducts.domain.entities.Category
import com.mobiquity.mobproducts.domain.entities.Product
import com.mobiquity.mobproducts.domain.usecase.ProductsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductsViewModel(private val useCase: ProductsUseCase) : BaseViewModel() {
    private val productsLiveData = MutableLiveData<Result<List<Category>>>()
    private val chosenProductLiveData = MutableLiveData<Product>()

    fun getProducts(): LiveData<Result<List<Category>>> {
        return productsLiveData
    }

    fun getChosenProduct(): LiveData<Product> {
        return chosenProductLiveData
    }

    fun setChosenProduct(chosenProduct: Product) {
        chosenProductLiveData.value = chosenProduct
    }

    fun fetchProducts() {
        compositeDisposable.add(useCase.getProducts()
            .subscribeOn(
                Schedulers.io()
            ).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe {
                productsLiveData.value = Result.success(it)
            })
    }

}