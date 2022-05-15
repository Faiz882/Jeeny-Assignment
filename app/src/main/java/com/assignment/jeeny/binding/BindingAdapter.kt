package com.assignment.jeeny.binding

import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("binding:image_url")
    fun imageUrl(imageView: SimpleDraweeView, imageUrl: String?) {
        imageView.load(imageUrl)
    }

    private fun SimpleDraweeView.load(imageUrl: String?) {
        if (imageUrl.isNullOrEmpty()) return
        hierarchy.fadeDuration = 200
        setImageURI(imageUrl)
    }
}