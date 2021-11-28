package com.kpstv.newsapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kpstv.mvvmnewsapp.categories.ui.CategoryFragment
import com.kpstv.mvvmnewsapp.extensions.viewBinding
import com.kpstv.navigation.Destination
import com.kpstv.navigation.FragmentNavigator
import com.kpstv.navigation.canFinish
import com.kpstv.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Main : AppCompatActivity(), FragmentNavigator.Transmitter {

  private lateinit var navigator: FragmentNavigator
  override fun getNavigator(): FragmentNavigator = navigator

  private val binding by viewBinding(ActivityMainBinding::inflate)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    navigator = FragmentNavigator.with(this, savedInstanceState)
      .initialize(binding.root, Destination.of(CategoryFragment::class))
  }

  override fun onBackPressed() {
    if (navigator.canFinish()) {
      super.onBackPressed()
    }
  }
}