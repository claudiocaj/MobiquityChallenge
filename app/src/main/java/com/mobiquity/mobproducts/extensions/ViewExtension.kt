package com.mobiquity.mobproducts.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mobiquity.mobproducts.R

fun View.visible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun ImageView.load(imageAddress: String) {
    Glide.with(this)
        .load(imageAddress)
        .error(R.drawable.ic_no_photo)
        .placeholder(R.drawable.ic_loading)
        .centerCrop()
        .into(this)
}

fun ImageView.loadWithoutPlaceHolder(imageAddress: String) {
    Glide.with(this)
        .load(imageAddress)
        .error(R.drawable.ic_no_photo)
        .centerCrop()
        .into(this)
}