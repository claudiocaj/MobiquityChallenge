package com.mobiquity.mobproducts.domain.entities

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val imageUrl: String,
    @SerializedName("salePrice")
    val saleDescription: Price
)
