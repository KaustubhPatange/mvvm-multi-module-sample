package com.kpstv.mvvmnewsapp.categories.data.model

import com.kpstv.bindings.AutoGenerateConverter
import com.kpstv.bindings.ConverterType

@AutoGenerateConverter(using = ConverterType.MOSHI)
internal data class Categories(
    val categories: List<String>
)