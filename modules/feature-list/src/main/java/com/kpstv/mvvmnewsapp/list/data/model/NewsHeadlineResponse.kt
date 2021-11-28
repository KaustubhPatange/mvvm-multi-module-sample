package com.kpstv.mvvmnewsapp.list.data.model

internal data class NewsHeadlineResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) {
    internal data class Article(
        val author: Any,
        val content: String,
        val description: String,
        val publishedAt: String,
        val source: Source,
        val title: String,
        val url: String,
        val urlToImage: String?
    )

    internal data class Source(
        val id: Any,
        val name: String
    )
}