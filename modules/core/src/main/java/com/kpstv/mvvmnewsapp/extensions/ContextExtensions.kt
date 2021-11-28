package com.kpstv.mvvmnewsapp.extensions

import android.content.Context
import android.view.LayoutInflater

val Context.layoutInflator : LayoutInflater
  get() = LayoutInflater.from(this)