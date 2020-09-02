package com.mobiquity.mobproducts.domain.entities

data class Product(
    val id: String,
    val name: String,
    val imageUrl: String,
    val saleDescription: Price
)
