package com.mobiquity.mobproducts.extensions

import com.mobiquity.mobproducts.BuildConfig
import java.text.NumberFormat
import java.util.*

fun String.currencyFormat(currency: String): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.currency = Currency.getInstance(currency)

    return format.format(this.toFloat())
}

fun String.getImageRequestFormat(): String = BuildConfig.API_URL + this.subSequence(1, this.length)
