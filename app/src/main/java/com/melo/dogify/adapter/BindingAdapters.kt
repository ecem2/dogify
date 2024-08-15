package com.melo.dogify.adapter

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.core.content.ContextCompat
import com.melo.dogify.model.CardModel

@BindingAdapter("cardBackground")
fun setCardBackground(view: ConstraintLayout, cardModel: CardModel?) {
    cardModel?.let {
        val drawable = ContextCompat.getDrawable(view.context, cardModel.image)
        view.background = drawable
    }
}

@BindingAdapter("cardImage")
fun setCardImage(view: ImageView, imageRes: Int) {
    view.setImageResource(imageRes)
}
