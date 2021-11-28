package com.example.challenge

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class BindingAdapters {
    companion object {

        @BindingAdapter(value = ["image_url", "place_holder", "error_drawable"], requireAll = false)
        fun loadImage(imageView: ImageView, imageUrl: String, placeHolder: Drawable, errorDrawable: Drawable) {
            FrescoLoadingImage.instance?.loadImage(imageView, imageUrl, placeHolder, errorDrawable)
        }
    }


}
