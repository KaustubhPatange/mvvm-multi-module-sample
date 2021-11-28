package com.kpstv.mvvmnewsapp.list.ui

import android.location.SettingInjectorService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.kpstv.mvvmnewsapp.list.data.api.NewsApi
import com.kpstv.mvvmnewsapp.list.data.localized.NewsDataSourceFactory
import com.kpstv.mvvmnewsapp.list.data.model.NewsListing
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ListViewModel @Inject constructor(
  private val newsApi: NewsApi
) : ViewModel() {

  fun fetchPaging(category: String, country: String) : NewsListing {
    val sourceFactory = NewsDataSourceFactory(
      scope = viewModelScope,
      category = category,
      country = country,
      newsApi = newsApi
    )

    val pageConfig = PagedList.Config.Builder()
      .setPageSize(20)
      .setEnablePlaceholders(false)
      .setPrefetchDistance(10)
      .build()

    return NewsListing(
      articles = sourceFactory.toLiveData(pageConfig),
      refreshState = sourceFactory.initLoadState.asLiveData(),
      loadMoreState = sourceFactory.loadMoreState.asLiveData(),
      onRefresh = {
        sourceFactory.source?.invalidate()
      }
    )
  }
}