package com.melo.dogify.viewmodel

import androidx.lifecycle.ViewModel
import com.melo.dogify.R
import com.melo.dogify.model.ArticleModel
import com.melo.dogify.model.CardModel
import com.melo.dogify.model.TrainingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.prefs.Preferences
import javax.inject.Inject

@HiltViewModel
class SoundsViewModel @Inject constructor(
    val preferences: Preferences
) : BaseViewModel() {

    private val _cardList: ArrayList<CardModel> = ArrayList()
    val cardList: List<CardModel>
        get() = _cardList
    private val _foodCardList: ArrayList<CardModel> = ArrayList()

    val articleList: List<ArticleModel>
        get() = _articleList
    private val _articleList: ArrayList<ArticleModel> = ArrayList()

    val foodCardList: List<CardModel>
        get() = _foodCardList
    private val _trainingList: ArrayList<TrainingModel> = ArrayList()
    val trainingList: List<TrainingModel>
        get() = _trainingList


    init {
        getCardList()
        getFoodCardList()
        getTrainingList()
        getArticleList()
    }

    private fun getTrainingList() {
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_one,
                textTitle = "Food",
                text = "When you make food at home for\n" +
                        "your dog, you are ensured that your\n dog is eating food tat meets\n" +
                        "your standards.",
            )
        )
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_two,
                textTitle = "Articles",
                text = "Make sure you give your dog lot’s\n" +
                        "of attention when she/he’s doing\n" +
                        "the right thing.",
            )
        )
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_three,
                textTitle = "Biting",
                text = "Puppy biting or nipping starts out\n" +
                        "as a bit of fun, but needs to be controllled quickly to avoid ongoing\n" +
                        "problems.",
            )
        )
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_four,
                textTitle = "Obidence",
                text = "Propper dog obedience tarining\n" +
                        "Should accomplish opens up a\n" +
                        "clear line of.",
            )
        )
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_five,
                textTitle = "Barking",
                text = "Propper dog obedience tarining\n" +
                        "Should accomplish opens up a\n" +
                        "clear line of.",
            )
        )

    }
    private fun getCardList() {
        _cardList.add(
            CardModel(
                image = R.drawable.dog_first,
                text = "Anxious",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.second_dog,
                text = "Call",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.third_dog,
                text = "Cold",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.fourth_dog,
                text = "Come here",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.fifth_dog,
                text = "Comfortable",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.sixth_dog,
                text = "Feeding",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.seventh_dog,
                text = "Happy",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.eighth_dog,
                text = "Hug",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.ninth_dog,
                text = "Hot",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.tenth_dog,
                text = "Hungry",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.eleventh_dog,
                text = "Joyful",
            )
        )

        _cardList.add(
            CardModel(
                image = R.drawable.twelfth_dog,
                text = "Love",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.thirteenth_dog,
                text = "Know",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.fourteenth_dog,
                text = "Playful",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.fifteenth_dog,
                text = "Surprised",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.sixteenth_dog,
                text = "Scared",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.seventeenth_dog,
                text = "I love you",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.eighteenth_dog,
                text = "No understand",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.nineteenth_dog,
                text = "Whining",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.twentieth_dog,
                text = "So happy",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.twenty_first_dog,
                text = "Uncomfortable",
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.twenty_second_dog,
                text = "Thirsty",
            )
        )
    }
    private fun getFoodCardList() {
        _foodCardList.add(
            CardModel(
                image = R.drawable.apple,
                text = "Apple",
            )
        )
        _foodCardList.add(
            CardModel(
                image = R.drawable.banana,
                text = "Banana",
            )
        )
        _foodCardList.add(
            CardModel(
                image = R.drawable.blueberry,
                text = "Bluberry",
            )
        )
        _foodCardList.add(
            CardModel(
                image = R.drawable.broccoli,
                text = "Broccoli",
            )
        )
        _foodCardList.add(
            CardModel(
                image = R.drawable.carrot,
                text = "Carrot",
            )
        )
        _foodCardList.add(
            CardModel(
                image = R.drawable.beet,
                text = "Beet",
            )
        )
        }
    private fun getArticleList() {
        _articleList.add(
            ArticleModel(
                image = R.drawable.article_read,
                textTitle = "My doggies cries \n" +
                        "when I leave him/her\n" +
                        "alone ",
                text = R.string.read_now,
            )
        )
        _articleList.add(
            ArticleModel(
                image = R.drawable.article_read,
                textTitle = "My doggies cries \n" +
                        "when I leave him/her\n" +
                        "alone ",
                text = R.string.read_now,
            )
        )
        _articleList.add(
            ArticleModel(
                image = R.drawable.article_read,
                textTitle = "My doggies cries \n" +
                        "when I leave him/her\n" +
                        "alone ",
                text = R.string.read_now,
            )
        )
        _articleList.add(
            ArticleModel(
                image = R.drawable.article_premium,
                textTitle = "My doggies cries \n" +
                        "when I leave him/her\n" +
                        "alone ",
                text = R.string.read_now,
            )
        )
    }

}