package com.melo.dogify.extensions

import android.content.res.Resources
import androidx.annotation.DimenRes

fun Resources.dimenToPx(@DimenRes dimen: Int): Int {
    return getDimensionPixelSize(dimen)
}
