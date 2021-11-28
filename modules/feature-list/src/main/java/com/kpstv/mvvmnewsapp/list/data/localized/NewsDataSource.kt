package com.kpstv.mvvmnewsapp.list.data.localized

import androidx.paging.PageKeyedDataSource
import com.kpstv.mvvmnewsapp.list.data.NetworkState
import com.kpstv.mvvmnewsapp.list.data.api.NewsApi
import com.kpstv.mvvmnewsapp.list.data.model.NewsHeadlineResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.ceil

internal class NewsDataSource(
  private val scope: CoroutineScope,
  private val initLoadState: MutableStateFlow<NetworkState>,
  private val loadMoreState: MutableStateFlow<NetworkState>,
  private val category: String,
  private val country: String,
  private val newsApi: NewsApi
) : PageKeyedDataSource<Int, NewsHeadlineResponse.Article>() {

  override fun loadInitial(
    params: LoadInitialParams<Int>,
    callback: LoadInitialCallback<Int, NewsHeadlineResponse.Article>
  ) {
    scope.launch {
      initLoadState.emit(NetworkState.LOADING)
      try {
        val news = newsApi.fetchHeadlines(
          category = category,
          country = country,
          page = 1,
          pageSize = params.requestedLoadSize
        )
        if (news.articles.isEmpty()) throw IllegalStateException("Cannot be empty")
        callback.onResult(news.articles, null, 2)
        initLoadState.emit(NetworkState.IDLE)
      } catch (e : Exception) {
        initLoadState.emit(NetworkState.ERROR)
      }
    }
  }

  override fun loadAfter(
    params: LoadParams<Int>,
    callback: LoadCallback<Int, NewsHeadlineResponse.Article>
  ) {
    scope.launch {
      if (loadMoreState.value == NetworkState.LOADING) return@launch
      loadMoreState.emit(NetworkState.LOADING)
      try {
        val news = newsApi.fetchHeadlines(
          category = category,
          country = country,
          page = 1,
          pageSize = params.requestedLoadSize
        )
        val clampKey = ceil(news.totalResults.toDouble() / params.requestedLoadSize.toDouble()).toInt()
        val key = if (params.key == clampKey) null else params.key + 1
        callback.onResult(news.articles, key)
        loadMoreState.emit(NetworkState.IDLE)
      } catch (e: Exception) {
        loadMoreState.emit(NetworkState.ERROR)
      }
    }
  }

  override fun loadBefore(
    params: LoadParams<Int>,
    callback: LoadCallback<Int, NewsHeadlineResponse.Article>
  ) {
    // we don't need this
  }

}