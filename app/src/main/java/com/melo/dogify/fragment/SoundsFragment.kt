package com.melo.dogify.fragment


import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.melo.dogify.R
import com.melo.dogify.adapter.SoundsAdapter
import com.melo.dogify.core.common.Constants.INTERSTITIAL_ID
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentSoundsBinding
import com.melo.dogify.model.CardModel
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask


@AndroidEntryPoint

class SoundsFragment : BaseFragment<SoundsViewModel, FragmentSoundsBinding>(),
    SoundsAdapter.ItemClickListener {

    private val soundsAdapter: SoundsAdapter by lazy {
        SoundsAdapter(
            requireContext(),
            this@SoundsFragment

        )
    }

    private var selectedCard: CardModel? = null
    private var selectedItemPosition: Int = 0
    private var mediaPlayer: MediaPlayer? = null
    private var mInterstitialAd: InterstitialAd? = null


    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_sounds

    override fun onInitDataBinding() {
        setupCardStyle()

    }

    private fun setupCardStyle() {
        viewBinding.rvDog.apply {
            adapter = soundsAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
        }

        viewModel.cardList.forEach { card ->
            card.isPremium = hasAccessToSound(card.mp3Title)
            card.premiumPhoto = when {
                card.isPremium -> R.drawable.ic_free
                card.premiumPhoto == R.drawable.ic_premium -> R.drawable.ic_premium
                card.premiumPhoto == R.drawable.ic_free -> R.drawable.ic_free
                else -> R.drawable.ic_advert
            }
        }

        soundsAdapter.submitList(viewModel.cardList)
    }

    private fun loadInterAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            requireContext(),
            INTERSTITIAL_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("AdMob", "Ad failed to load: ${adError.message}")
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("AdMob", "Ad loaded successfully.")
                    mInterstitialAd = interstitialAd
                    showVideoAd {
                        selectedCard?.let { card ->
                            allowSoundFor24Hours(card.mp3Title)
                        }
                    }
                }
            }
        )
    }

    private fun showVideoAd(onAdCompleted: () -> Unit) {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d("AdMob", "Ad was dismissed.")
                    onAdCompleted()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    Log.d("AdMob", "Ad failed to show.")
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("AdMob", "Ad showed fullscreen content.")
                    mInterstitialAd = null
                }
            }
            mInterstitialAd?.show(requireActivity())
        } else {
            Log.d("AdMob", "The interstitial ad wasn't ready yet.")
        }
    }

    private fun handleCardClick(card: CardModel) {
        when (card.premiumPhoto) {
            R.drawable.ic_advert -> {
                if (!hasAccessToSound(card.mp3Title)) {
                    showAdForSound(card)
                } else {
                    playSound(card.mp3Title)
                }
            }

            R.drawable.ic_premium -> {
                navigateToSubscriptionScreen()
            }

            else -> {
                playSound(card.mp3Title)
            }
        }
    }

    private fun hasAccessToSound(mp3Title: Int): Boolean {
        val currentTime = System.currentTimeMillis()
        val sharedPreferences =
            requireContext().getSharedPreferences("sound_prefs", Context.MODE_PRIVATE)
        val expirationTime = sharedPreferences.getLong("sound_$mp3Title", 0)
        return currentTime <= expirationTime
    }

    private fun showAdForSound(card: CardModel) {
        if (mInterstitialAd == null) {
            loadInterAd()
        } else {
            showVideoAd {
                allowSoundFor24Hours(card.mp3Title)
            }
        }
    }

    private fun allowSoundFor24Hours(mp3Title: Int) {
        val currentTime = System.currentTimeMillis()
        val expirationTime = currentTime + 24 * 60 * 60 * 1000
        saveExpirationTimeForSound(mp3Title, expirationTime)

        val card = viewModel.cardList.find { it.mp3Title == mp3Title }
        card?.let {
            it.isPremium = true
            it.premiumPhoto = R.drawable.ic_free
        }
        soundsAdapter.notifyDataSetChanged()

        playSound(mp3Title)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                requireActivity().runOnUiThread {
                    card?.let {
                        it.isPremium = false
                        it.premiumPhoto = R.drawable.ic_advert
                    }
                    soundsAdapter.notifyDataSetChanged()
                }
            }
        }, 24 * 60 * 60 * 1000)
    }


    private fun saveExpirationTimeForSound(mp3Title: Int, expirationTime: Long) {
        val sharedPreferences =
            requireContext().getSharedPreferences("sound_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putLong("sound_$mp3Title", expirationTime).apply()
    }


    override fun onResume() {
        super.onResume()
        soundsAdapter.notifyDataSetChanged()
    }

    private fun playSound(mp3Title: Int) {
        val card = viewModel.cardList.find { it.mp3Title == mp3Title }
        if (card != null) {
            if (hasAccessToSound(mp3Title)) {
                mediaPlayer?.let {
                    if (it.isPlaying) {
                        it.stop()
                    }
                    it.release()
                }

                mediaPlayer = MediaPlayer.create(requireContext(), mp3Title)
                mediaPlayer?.start()
            } else {
                card.isPremium = false
                card.premiumPhoto = R.drawable.ic_advert
                soundsAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun navigateToSubscriptionScreen() {
        findNavController().navigate(R.id.action_soundsFragment_to_premiumScreenPurchaseFragment)
    }

    override fun onItemClick(item: CardModel) {
        val previousPosition = selectedItemPosition
        selectedItemPosition = viewModel.cardList.indexOf(item)
        selectedCard = item

        soundsAdapter.notifyItemChanged(previousPosition)
        soundsAdapter.notifyItemChanged(selectedItemPosition)

        handleCardClick(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}
//class SoundsFragment : BaseFragment<SoundsViewModel, FragmentSoundsBinding>(),
//    SoundsAdapter.ItemClickListener {
//
//    private val soundsAdapter: SoundsAdapter by lazy {
//        SoundsAdapter(
//            requireContext(),
//            this@SoundsFragment
//
//        )
//    }
//
//    private var selectedCard: CardModel? = null
//    private var selectedItemPosition: Int = 0
//    private var mediaPlayer: MediaPlayer? = null
//
//
//    override fun viewModelClass() = SoundsViewModel::class.java
//
//    override fun getResourceLayoutId() = R.layout.fragment_sounds
//
//    override fun onInitDataBinding() {
//        setupCardStyle()
//
//    }
//
//    private fun setupCardStyle() {
//        viewBinding.rvDog.apply {
//            adapter = soundsAdapter
//            layoutManager = GridLayoutManager(requireContext(), 3)
//            setHasFixedSize(true)
//        }
//        soundsAdapter.submitList(viewModel.cardList)
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//    }
//
//    private fun playSound() {
//        selectedCard?.let { card ->
//            try {
//                mediaPlayer?.release()
//                mediaPlayer = MediaPlayer.create(requireContext(), card.mp3Title)
//                Log.d("ecemm", "Playing sound: ${card.text}")
//
//                if (mediaPlayer == null) {
//                    Log.d("ecemm", "MediaPlayer null, sound file not found!")
//                    return
//                }
//                mediaPlayer?.start()
//                mediaPlayer?.setOnCompletionListener {
//                    mediaPlayer?.release()
//                    mediaPlayer = null
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        } ?: run {
//            Log.d("ecemm", "No card selected!")
//        }
//    }
//
//
//    override fun onItemClick(item: CardModel) {
//        val previousPosition = selectedItemPosition
//        selectedItemPosition = viewModel.cardList.indexOf(item)
//        selectedCard = item
//
//        soundsAdapter.notifyItemChanged(previousPosition)
//        soundsAdapter.notifyItemChanged(selectedItemPosition)
//
//        playSound()
//    }
//
//
//}