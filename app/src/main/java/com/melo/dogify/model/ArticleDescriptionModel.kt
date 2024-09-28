package com.melo.dogify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDescriptionModel(
    val titleText: Int,
    val text: Int
) : Parcelable
