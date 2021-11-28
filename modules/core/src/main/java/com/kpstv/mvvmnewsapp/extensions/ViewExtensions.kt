package com.kpstv.mvvmnewsapp.extensions

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

val View.layoutInflator : LayoutInflater
  get() = this.context.layoutInflator

fun View.show() {
  visibility = View.VISIBLE
}

fun View.hide() {
  visibility = View.INVISIBLE
}

fun View.collapse() {
  visibility = View.GONE
}

