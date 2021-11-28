package com.kpstv.mvvmnewsapp.list.data.localized

import androidx.paging.DataSource
import com.kpstv.mvvmnewsapp.list.data.NetworkState
import com.kpstv.mvvmnewsapp.list.data.api.NewsApi
import com.kpstv.mvvmnewsapp.list.data.model.NewsHeadlineResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

internal class NewsDataSourceFactory(
  private val scope: CoroutineScope,
  private val category: String,
  private val country: String,
  private val newsApi: NewsApi
) : DataSource.Factory<Int, NewsHeadlineResponse.Article>() {

  val initLoadState = MutableStateFlow(NetworkState.IDLE)
  val loadMoreState = MutableStateFlow(NetworkState.IDLE)

  var source : NewsDataSource? = null
    private set
  override fun create(): DataSource<Int, NewsHeadlineResponse.Article> {
    val newsDataSource = NewsDataSource(
      scope = scope,
      category = category,
      country = country,
      newsApi = newsApi,
      initLoadState = initLoadState,
      loadMoreState = loadMoreState
    )
    source = newsDataSource
    return newsDataSource
  }
}