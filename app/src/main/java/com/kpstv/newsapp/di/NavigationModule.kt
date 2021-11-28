package com.kpstv.newsapp.di

import com.kpstv.mvvmnewsapp.categories.ui.navigation.CategoryNavigate
import com.kpstv.newsapp.ui.navigation.CategoryNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

  @Binds
  abstract fun categoryNavigate(categoryNavigationImpl: CategoryNavigationImpl) : CategoryNavigate
}