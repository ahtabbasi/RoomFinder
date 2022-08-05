package com.ahtabbasi.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

object ImageUtils {

    private const val LOADER_RADIUS = 25f
    private const val LOADER_STROKE = 4f

    fun loadImageViewWithGlide(iv: ImageView, url: String) {
        with(iv) {

            val circularProgressDrawable = CircularProgressDrawable(context).apply {
                centerRadius = LOADER_RADIUS
                strokeWidth = LOADER_STROKE
                start()
            }

            Glide.with(context)
                .load(url)
                .placeholder(circularProgressDrawable)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(this)
        }
    }
}