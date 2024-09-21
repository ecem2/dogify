package com.melo.dogify.model

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.core.content.ContextCompat
import com.melo.dogify.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardModel(
    val image: Int,
    val premiumPhoto: Int,
    val text: String,
    val mp3Title: Int,
    val isPremium: Boolean
) : Parcelable

