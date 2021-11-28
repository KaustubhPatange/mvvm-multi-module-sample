package com.kpstv.mvvmnewsapp.list.di

import com.kpstv.mvvmnewsapp.list.BuildConfig
import com.kpstv.mvvmnewsapp.list.data.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
internal class NetworkModule {

  @Provides
  fun newsApi(): NewsApi {
    val interceptor = Interceptor { chain ->
      val url = chain.request().url().newBuilder().addQueryParameter("apiKey", BuildConfig.NewsApiKey).build()
      val request = chain.request()
        .newBuilder()
        .url(url)
        .build()
      return@Interceptor chain.proceed(request)
    }
    return NewsApi.getBuilder()
      .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create(NewsApi::class.java)
  }
}