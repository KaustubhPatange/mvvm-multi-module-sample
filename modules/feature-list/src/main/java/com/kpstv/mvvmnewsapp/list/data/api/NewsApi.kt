package com.kpstv.mvvmnewsapp.list.data.api

import com.kpstv.mvvmnewsapp.list.data.model.NewsHeadlineResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NewsApi {

  @GET("top-headlines")
  suspend fun fetchHeadlines(
    @Query("category") category: String,
    @Query("country") country: String,
    @Query("page") page: Int,
    @Query("pageSize") pageSize: Int = 20
  ): NewsHeadlineResponse

  companion object {
    private const val BaseUrl = "https://newsapi.org/v2/"

    fun getBuilder(): Retrofit.Builder {
      return Retrofit.Builder().baseUrl(BaseUrl)
    }
  }
}