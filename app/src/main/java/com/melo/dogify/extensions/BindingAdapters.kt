package com.melo.dogify.extensions

import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("app:resIdToString")
fun setResIdToString(view: TextView, resId: Int) {
    view.text = view.context.getString(resId)
}
@BindingAdapter("cardImage")
fun setCardImage(view: ImageView, imageRes: Int) {
    view.setImageResource(imageRes)
}
