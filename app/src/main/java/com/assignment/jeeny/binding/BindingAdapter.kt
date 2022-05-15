package com.assignment.jeeny.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @BindingAdapter(value = ["binding:imageUrl"], requireAll = true)
    fun imageUrl(imageView: ImageView, url: ImageView){
        // todo load image
    }
}