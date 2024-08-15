package com.melo.dogify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainingModel(
    val image: Int,
    val textTitle: String,
    val text: String
) : Parcelable
