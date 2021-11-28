package com.kpstv.mvvmnewsapp.list.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kpstv.mvvmnewsapp.extensions.layoutInflator
import com.kpstv.mvvmnewsapp.extensions.loadImage
import com.kpstv.mvvmnewsapp.list.R
import com.kpstv.mvvmnewsapp.list.data.model.NewsHeadlineResponse
import com.kpstv.mvvmnewsapp.list.databinding.ListItemBannerBinding
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

internal class ListFragmentAdapter() : PagedListAdapter<NewsHeadlineResponse.Article, ListFragmentAdapter.ItemHolder>(HolderDiffUtil) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
    return ItemHolder(ListItemBannerBinding.inflate(parent.layoutInflator, parent, false))
  }

  override fun onBindViewHolder(holder: ItemHolder, position: Int) {
    getItem(position)?.let { holder.bind(it) }
  }

  class ItemHolder(private val binding: ListItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: NewsHeadlineResponse.Article) = with(binding) {
      item.urlToImage?.let { ivBanner.loadImage(it) }
      tvHeadline.text = item.title.split("-")[0].trim()

      val publishedString = format.parse(item.publishedAt)?.let { formatUsual.format(it) } ?: run { null }

      tvSource.text = root.context.getString(R.string.subtitle, publishedString, item.source.name)
    }

    companion object {
      private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT)
      private val formatUsual = SimpleDateFormat("E, dd MMM yyyy HH:mm", Locale.ROOT)
    }
  }

  private object HolderDiffUtil : DiffUtil.ItemCallback<NewsHeadlineResponse.Article>() {
    override fun areItemsTheSame(
      oldItem: NewsHeadlineResponse.Article,
      newItem: NewsHeadlineResponse.Article
    ): Boolean = oldItem === newItem

    override fun areContentsTheSame(
      oldItem: NewsHeadlineResponse.Article,
      newItem: NewsHeadlineResponse.Article
    ): Boolean = oldItem == newItem
  }
}