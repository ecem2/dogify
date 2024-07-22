package com.melo.dogify.viewmodel

import androidx.lifecycle.ViewModel
import com.melo.dogify.R
import com.melo.dogify.model.CardModel
import java.util.prefs.Preferences
import javax.inject.Inject

class SoundsViewModel @Inject constructor(
    val preferences: Preferences
):BaseViewModel() {

    private val _cardList: ArrayList<CardModel> = ArrayList()
    val cardList: List<CardModel>
        get() = _cardList

    init {
        getCardList()
    }

    private fun getCardList() {
        _cardList.add(
            CardModel(
                cardColor = R.color.firstColor,
                rectangleColor = R.color.firstTextColor,
                image = R.drawable.dog_photo,
                text = "Anxious",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.secondColor ,
                rectangleColor = R.color.secondTextColor,
                image = R.drawable.second_dog,
                text = "Call",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.thirdColor,
                rectangleColor = R.color.thirdTextColor,
                image = R.drawable.third_dog,
                text = "Cold",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.thirdColor,
                rectangleColor = R.color.thirdTextColor,
                image = R.drawable.fourth_dog,
                text = "Come here",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.fourthColor,
                rectangleColor = R.color.fourthTextColor,
                image = R.drawable.fifth_dog,
                text = "Comfortable",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.firstColor,
                rectangleColor = R.color.firstTextColor,
                image = R.drawable.sixth_dog,
                text = "Feeding",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.fifthColor,
                rectangleColor = R.color.fifthTextColor,
                image = R.drawable.seventh_dog,
                text = "Happy",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.sixthColor,
                rectangleColor = R.color.sixTextColor,
                image = R.drawable.eighth_dog,
                text = "Hug",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.thirdColor,
                rectangleColor = R.color.thirdTextColor,
                image = R.drawable.ninth_dog,
                text = "Hot",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.seventhTextColor,
                image = R.drawable.tenth_dog,
                text = "Hungry",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.fourthColor,
                rectangleColor = R.color.fourthTextColor,
                image = R.drawable.eleventh_dog,
                text = "Joyful",
            )
        )

        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.fifthTextColor,
                image = R.drawable.twelfth_dog,
                text = "Love",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.ninthTextColor,
                image = R.drawable.thirteenth_dog,
                text = "Know",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = R.color.secondColor,
                rectangleColor = R.color.secondTextColor,
                image = R.drawable.fourteenth_dog,
                text = "Playful",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.tenthTextColor,
                image = R.drawable.fifteenth_dog,
                text = "Surprised",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.eleventhTextColor,
                image = R.drawable.sixteenth_dog,
                text = "Scared",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.twelthTextColor,
                image = R.drawable.seventeenth_dog,
                text = "I love you",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.thirteenthTextColor,
                image = R.drawable.eighteenth_dog,
                text = "No understand",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.fourteenthTextColor,
                image = R.drawable.nineteenth_dog,
                text = "Whining",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.fifteenthTextColor,
                image = R.drawable.twentieth_dog,
                text = "So happy",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.sixteenthTextColor,
                image = R.drawable.twenty_first_dog,
                text = "Uncomfortable",
            )
        )
        _cardList.add(
            CardModel(
                cardColor = null,
                rectangleColor = R.color.seventeenthTextColor,
                image = R.drawable.twenty_second_dog,
                text = "Thirsty",
            )
        )
    }
}