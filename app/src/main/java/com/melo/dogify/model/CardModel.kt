package com.melo.dogify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardModel(
    val image: Int,
    var premiumPhoto: Int,
    val text: Int,
    val mp3Title: Int,
    var isPremium: Boolean
) : Parcelable

