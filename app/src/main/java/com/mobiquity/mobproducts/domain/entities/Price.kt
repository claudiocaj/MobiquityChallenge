package com.mobiquity.mobproducts.domain.entities

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String
)
