package com.kpstv.mvvmnewsapp.list.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kpstv.mvvmnewsapp.extensions.collapse
import com.kpstv.mvvmnewsapp.extensions.show
import com.kpstv.mvvmnewsapp.extensions.viewBinding
import com.kpstv.mvvmnewsapp.list.R
import com.kpstv.mvvmnewsapp.list.data.NetworkState
import com.kpstv.mvvmnewsapp.list.databinding.FragmentListBinding
import com.kpstv.navigation.BaseArgs
import com.kpstv.navigation.ValueFragment
import com.kpstv.navigation.getKeyArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class ListFragment : ValueFragment(R.layout.fragment_list) {

  private val binding by viewBinding(FragmentListBinding::bind)
  private val viewModel by viewModels<ListViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val args = getKeyArgs<Args>()

    val listing = viewModel.fetchPaging(country = args.country, category = args.category)

    val adapter = ListFragmentAdapter()

    listing.articles.observe(viewLifecycleOwner) { articles ->
      adapter.submitList(articles)
    }

    binding.recyclerViewList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    binding.recyclerViewList.adapter = adapter

    listing.refreshState.observe(viewLifecycleOwner) { state ->
      when(state) {
        NetworkState.IDLE -> binding.swipeRefreshList.isRefreshing = false
        NetworkState.LOADING -> binding.swipeRefreshList.isRefreshing = true
        else -> binding.btnRetry.show()
      }
    }

    binding.swipeRefreshList.setOnRefreshListener {
      listing.onRefresh()
    }


    binding.btnRetry.setOnClickListener {
      it.collapse()
      listing.onRefresh()
    }
  }

  @Parcelize
  data class Args(val country: String, val category: String) : BaseArgs()
}