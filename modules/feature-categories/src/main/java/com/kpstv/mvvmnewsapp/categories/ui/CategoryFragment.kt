package com.kpstv.mvvmnewsapp.categories.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.kpstv.mvvmnewsapp.categories.R
import com.kpstv.mvvmnewsapp.categories.data.model.CategoriesConverter
import com.kpstv.mvvmnewsapp.categories.databinding.FragmentCategoriesBinding
import com.kpstv.mvvmnewsapp.categories.ui.navigation.CategoryNavigate
import com.kpstv.mvvmnewsapp.extensions.viewBinding
import com.kpstv.navigation.ValueFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment : ValueFragment(R.layout.fragment_categories) {
  private val binding by viewBinding(FragmentCategoriesBinding::bind)
  private val viewModel by viewModels<CategoryViewModel>()

  @Inject lateinit var categoryNavigate: CategoryNavigate

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val json = resources.openRawResource(R.raw.categories).bufferedReader().readText()
    CategoriesConverter.fromStringToCategories(json)?.categories?.forEach { category ->
      binding.chipGroup.addView(Chip(requireContext()).apply {
        text = category
        tag = category
      })
    }

    viewModel.checkedTag.observe(viewLifecycleOwner) { tag ->
      binding.chipGroup.findViewWithTag<Chip>(tag)?.isChecked = true
    }

    binding.chipGroup.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
      invalidateButton()
    }

    binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
      val tag = binding.chipGroup.findViewById<Chip>(checkedId)?.tag as? String
      viewModel.setCheckedTag(tag)
      invalidateButton()
    }

    binding.btnNext.setOnClickListener {
      val checkedChip = binding.chipGroup.findViewById<Chip>(binding.chipGroup.checkedChipId)
      categoryNavigate.goToList(category = checkedChip.text.toString(), country = "in")
    }
  }

  private fun invalidateButton() {
    binding.btnNext.isEnabled = binding.chipGroup.children.any { (it as Chip).isChecked }
  }
}