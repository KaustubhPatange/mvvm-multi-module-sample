package com.kpstv.mvvmnewsapp.list.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kpstv.mvvmnewsapp.list.data.NetworkState

internal data class NewsListing(
  val articles: LiveData<PagedList<NewsHeadlineResponse.Article>>,
  val refreshState : LiveData<NetworkState>,
  val loadMoreState : LiveData<NetworkState>,
  val onRefresh: () -> Unit
)