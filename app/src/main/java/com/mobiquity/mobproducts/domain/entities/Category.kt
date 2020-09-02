package com.mobiquity.mobproducts.domain.entities

class Category(
    val id: String,
    val name: String,
    val products: List<Product>
) {
}