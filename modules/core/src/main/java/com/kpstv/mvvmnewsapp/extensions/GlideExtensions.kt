package com.kpstv.mvvmnewsapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImage(url: String) {
  Glide.with(this).load(url).transition(
    DrawableTransitionOptions.withCrossFade()
  ).into(this)
}
