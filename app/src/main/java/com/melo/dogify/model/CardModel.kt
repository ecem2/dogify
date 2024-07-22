package com.melo.dogify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardModel(
    val cardColor: Int? = null,
    val rectangleColor: Int? = null,
    val image: Int? = null,
    val text: String? = null
) : Parcelable
