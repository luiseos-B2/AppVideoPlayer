package com.freecast.thatmovieapp.core.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.freecast.thatmovieapp.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .transform(CenterCrop())
        .placeholder(R.drawable.image_placeholder)
        .error(R.drawable.image_error)
        .into(this)
}