package com.example.challenge

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

class FrescoLoadingImage {

    companion object {
        var instance: FrescoLoadingImage? = null
            get() {
                if (field == null) {
                    field = FrescoLoadingImage()
                }
                return field
            }
    }

    fun loadImage(
        imageView: ImageView?,
        imageUrl: String?,
        placeHolder: Drawable?,
        errorDrawable: Drawable?
    ) {
        if (imageView is SimpleDraweeView) {
            val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
                .setProgressiveRenderingEnabled(true)
                .build()
            val controller: DraweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(imageView.controller)
                .setTapToRetryEnabled(false)
                .build()
            imageView.hierarchy.setPlaceholderImage(placeHolder)
            imageView.hierarchy.setFailureImage(errorDrawable)
            imageView.controller = controller
        } else {
            throw IllegalArgumentException(
                "Fresco only works with " +
                        "SimpleDraweeView"
            )
        }
    }

    fun loadImage(
        imageView: ImageView?,
        imageUrl: String?,
        placeHolder: Int,
        errorDrawable: Int
    ) {
        if (imageView is SimpleDraweeView) {
            val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
                .setProgressiveRenderingEnabled(true)
                .build()
            val controller: DraweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(imageView.controller)
                .setTapToRetryEnabled(false)
                .build()
            imageView.hierarchy.setPlaceholderImage(placeHolder)
            imageView.hierarchy.setFailureImage(errorDrawable)
            imageView.controller = controller
        } else {
            throw IllegalArgumentException(
                "Fresco only works with " +
                        "SimpleDraweeView"
            )
        }
    }
}