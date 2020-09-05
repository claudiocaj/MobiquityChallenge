package com.mobiquity.mobproducts.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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
        .into(this)
}

fun ImageView.loadWithoutPlaceHolder(imageAddress: String) {
    Glide.with(this)
        .load(imageAddress)
        .error(R.drawable.ic_no_photo)
        .into(this)
}