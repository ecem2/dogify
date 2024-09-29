package com.melo.dogify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleModel(
    val image: Int,
    val textTitle: Int,
    val text: Int
) : Parcelable