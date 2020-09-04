package com.mobiquity.mobproducts.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String
) : Parcelable
