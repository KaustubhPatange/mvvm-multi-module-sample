package com.kpstv.mvvmnewsapp.categories.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val checkedTagMutable = savedStateHandle.getLiveData<String?>("checked_state", null)
  val checkedTag : LiveData<String?> = checkedTagMutable

  fun setCheckedTag(value: String?) {
    savedStateHandle["checked_state"] = value
  }
}