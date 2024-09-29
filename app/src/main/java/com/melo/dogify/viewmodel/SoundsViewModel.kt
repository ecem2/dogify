package com.melo.dogify.viewmodel

import android.provider.Settings.System.getString
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.melo.dogify.R
import com.melo.dogify.model.ArticleDescriptionModel
import com.melo.dogify.model.ArticleModel
import com.melo.dogify.model.CardModel
import com.melo.dogify.model.FoodDescriptionModel
import com.melo.dogify.model.FoodModel
import com.melo.dogify.model.TrainingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.melo.dogify.preferences.Preferences
import javax.inject.Inject

@HiltViewModel
class SoundsViewModel @Inject constructor(
    val preferences: Preferences,
) : BaseViewModel() {

    private val _cardList: ArrayList<CardModel> = ArrayList()
    val cardList: List<CardModel>
        get() = _cardList
    private val _foodCardList: ArrayList<FoodModel> = ArrayList()
    val foodCardList: List<FoodModel>
        get() = _foodCardList
    val articleList: List<ArticleModel>
        get() = _articleList
    private val _articleList: ArrayList<ArticleModel> = ArrayList()
    val harmfulOnesList: List<FoodModel>
        get() = _harmfulOnesList
    private val _harmfulOnesList: ArrayList<FoodModel> = ArrayList()
    val carefulOnesList: List<FoodModel>
        get() = _carefulOnesList
    private val _carefulOnesList: ArrayList<FoodModel> = ArrayList()

    private val _trainingList: ArrayList<TrainingModel> = ArrayList()
    val trainingList: List<TrainingModel>
        get() = _trainingList
    private val _foodDescriptionList: MutableSet<FoodDescriptionModel> = mutableSetOf()
    val foodDescriptionList: Set<FoodDescriptionModel>
        get() = _foodDescriptionList

    val articleDescriptionModel: List<ArticleDescriptionModel>
        get() = _articleDescriptionModel
    private val _articleDescriptionModel: ArrayList<ArticleDescriptionModel> = ArrayList()

    init {
        getCardList()
        getFoodCardList()
        getTrainingList()
        getArticleList()
        getFoodHarmfulList()
        getFoodCarefulList()
    }
    private fun getTrainingList() {
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_one,
                textTitle = "Food",
                text = "When you make food at home for your dog, you are ensured that your dog is eating food tat meets your standards."
            )
        )
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_two,
                textTitle = "Articles",
                text = "Make sure you give your dog lot’s of attention when she/he’s doing the right thing."
            )
        )
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_three,
                textTitle = "Biting",
                text = "Puppy biting or nipping starts out as a bit of fun, but needs to be controllled quickly to avoid ongoing problems."
            )
        )
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_four,
                textTitle = "Obidence",
                text = "Propper dog obedience tarining Should accomplish opens up a clear line of.",
            )
        )
        _trainingList.add(
            TrainingModel(
                image = R.drawable.training_five,
                textTitle = "Barking",
                text = "Propper dog obedience tarining Should accomplish opens up a clear line of.",
            )
        )

    }

    class FoodViewModel : ViewModel() {
        var selectedText: String? = null
    }

    private fun getCardList() {
        _cardList.add(
            CardModel(
                image = R.drawable.dog_first,
                premiumPhoto = R.drawable.ic_advert,
                text = "Anxious",
                mp3Title = R.raw.sound_anxious_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.second_dog,
                premiumPhoto = R.drawable.ic_free,
                text = "Call",
                mp3Title = R.raw.sound_call_dog,
                isPremium = true
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.third_dog,
                premiumPhoto = R.drawable.ic_advert,
                text = "Cold",
                mp3Title = R.raw.sound_cold_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.fourth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "Come here",
                mp3Title = R.raw.sound_come_here_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.fifth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "Comfortable",
                mp3Title = R.raw.sound_comfortable_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.sixth_dog,
                premiumPhoto = R.drawable.ic_advert,
                text = "Feeding",
                mp3Title = R.raw.sound_feeding_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.seventh_dog,
                premiumPhoto = R.drawable.ic_free,
                text = "Happy",
                mp3Title = R.raw.sound_happy_dog,
                isPremium = true
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.eighth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "Hug",
                mp3Title = R.raw.sound_hug_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.ninth_dog,
                premiumPhoto = R.drawable.ic_advert,
                text = "Hot",
                mp3Title = R.raw.sound_hot_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.tenth_dog,
                premiumPhoto = R.drawable.ic_advert,
                text = "Hungry",
                mp3Title = R.raw.sound_hungry_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.eleventh_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "Joyful",
                mp3Title = R.raw.sound_joyful_dog,
                isPremium = false
            )
        )

        _cardList.add(
            CardModel(
                image = R.drawable.twelfth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "Love",
                mp3Title = R.raw.sound_lovely_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.thirteenth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "Know",
                mp3Title = R.raw.sound_know_me_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.fourteenth_dog,
                premiumPhoto = R.drawable.ic_advert,
                text = "Playful",
                mp3Title = R.raw.sound_lets_play_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.fifteenth_dog,
                premiumPhoto = R.drawable.ic_advert,
                text = "Surprised",
                mp3Title = R.raw.sound_feeding_dog,
                isPremium = true
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.sixteenth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "Scared",
                mp3Title = R.raw.sound_scared_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.seventeenth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "I love you",
                mp3Title = R.raw.sound_i_love_you_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.eighteenth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "No understand",
                mp3Title = R.raw.sound_no_understand,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.nineteenth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "Whining",
                mp3Title = R.raw.sound_whining_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.twentieth_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "So happy",
                mp3Title = R.raw.sound_so_happy_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.twenty_first_dog,
                premiumPhoto = R.drawable.ic_premium,
                text = "Uncomfortable",
                mp3Title = R.raw.sound_uncomfortable_dog,
                isPremium = false
            )
        )
        _cardList.add(
            CardModel(
                image = R.drawable.twenty_second_dog,
                premiumPhoto = R.drawable.ic_advert,
                text = "Thirsty",
                mp3Title = R.raw.sound_thirsty_dog,
                isPremium = false
            )
        )
    }

    private fun getFoodCardList() {
        _foodCardList.add(
            FoodModel(
                image = R.drawable.apple,
                text = R.string.apple,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.banana,
                text = R.string.banana,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.blueberry,
                text = R.string.blueberry,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.broccoli,
                text = R.string.broccoli,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.carrot,
                text = R.string.carrot,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.beet,
                text = R.string.beet,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.cucumber,
                text = R.string.cucumber,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.mango,
                text = R.string.mango,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.green_peas,
                text = R.string.green_peas,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.orange,
                text = R.string.orange,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.lettuce,
                text = R.string.lettuce,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.strawberry,
                text = R.string.strawberry,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.pineapple,
                text = R.string.pineapple,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.watermelon,
                text = R.string.watermelon,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.parsley,
                text = R.string.parsley,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.turnip,
                text = R.string.turnip,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.spinach,
                text = R.string.spinach,
            )
        )
        _foodCardList.add(
            FoodModel(
                image = R.drawable.bread,
                text = R.string.bread,
            )
        )
    }
    private fun getFoodCarefulList() {
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.cheese,
                text = R.string.cheese,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.cherries,
                text = R.string.cherries,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.egg,
                text = R.string.egg,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.honey,
                text = R.string.honey,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.ice_cream,
                text = R.string.ice_cream,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.juice,
                text = R.string.juice,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.milk,
                text = R.string.milk,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.peanut,
                text = R.string.peanut,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.pomegranate,
                text = R.string.pomegranate,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.tomato,
                text = R.string.tomato,
            )
        )
        _carefulOnesList.add(
            FoodModel(
                image = R.drawable.yogurt,
                text = R.string.yogurt,
            )
        )
    }
    private fun getFoodHarmfulList() {
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.alcohol,
                text = R.string.alcohol,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.avocado,
                text = R.string.avocado,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.candy,
                text = R.string.candy,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.chocolate,
                text = R.string.chocolate,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.coffee,
                text = R.string.coffee,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.bones,
                text = R.string.bones,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.fishbones,
                text = R.string.fishbones,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.garlic,
                text = R.string.garlic,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.grapes,
                text = R.string.grapes,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.leek,
                text = R.string.leek,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.lemon,
                text = R.string.lemon,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.mushroom,
                text = R.string.mushroom,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.onion,
                text = R.string.onion,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.tea,
                text = R.string.tea,
            )
        )
        _harmfulOnesList.add(
            FoodModel(
                image = R.drawable.tobacco,
                text = R.string.tobacco,
            )
        )
    }

    private fun getArticleList() {
        _articleList.add(
            ArticleModel(
                image = R.drawable.article_read,
                textTitle = R.string.first_article,
                text = R.string.read_now
            )
        )
        _articleList.add(
            ArticleModel(
                image = R.drawable.article_read,
                textTitle = R.string.second_article,
                text = R.string.read_now
            )
        )
        _articleList.add(
            ArticleModel(
                image = R.drawable.article_read,
                textTitle = R.string.third_article,
                text = R.string.read_now
            )
        )
        _articleList.add(
            ArticleModel(
                image = R.drawable.article_premium,
                textTitle = R.string.fourth_article,
                text = R.string.read_now
            )
        )
    }


}