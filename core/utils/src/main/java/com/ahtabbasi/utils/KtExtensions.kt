package com.ahtabbasi.utils

import android.view.View
import android.widget.ImageView
import com.ahtabbasi.utils.ImageUtils

fun ImageView.loadImage(url: String) = ImageUtils.loadImageViewWithGlide(this, url)

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}