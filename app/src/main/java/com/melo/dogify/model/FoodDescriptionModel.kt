package com.melo.dogify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodDescriptionModel(
    val image: Int,
    val titleText: Int,
    val text: Int
) : Parcelable
