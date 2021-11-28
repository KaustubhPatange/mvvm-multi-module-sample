package com.kpstv.newsapp.ui.navigation

import androidx.fragment.app.FragmentActivity
import com.kpstv.mvvmnewsapp.categories.ui.navigation.CategoryNavigate
import com.kpstv.mvvmnewsapp.list.ui.ListFragment
import com.kpstv.navigation.AnimationDefinition
import com.kpstv.navigation.FragmentNavigator
import javax.inject.Inject

class CategoryNavigationImpl @Inject constructor(
  private val activity: FragmentActivity
) : CategoryNavigate {
  override fun goToList(category: String, country: String) {
    if (activity is FragmentNavigator.Transmitter) {
      val navOptions = FragmentNavigator.NavOptions(
        args = ListFragment.Args(country = country, category = category),
        animation = AnimationDefinition.Fade,
        remember = true
      )
      activity.getNavigator().navigateTo(ListFragment::class, navOptions)
    }
  }
}