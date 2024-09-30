package com.melo.dogify.fragment


import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
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

class SoundsFragment : BaseFragment<SoundsViewModel, FragmentSoundsBinding>(), SoundsAdapter.ItemClickListener {

    private val soundsAdapter: SoundsAdapter by lazy {
        SoundsAdapter(requireContext(), this@SoundsFragment)
    }

    private var selectedCard: CardModel? = null
    private var selectedItemPosition: Int = 0
    private var mediaPlayer: MediaPlayer? = null
    private var mInterstitialAd: InterstitialAd? = null

    override fun viewModelClass() = SoundsViewModel::class.java
    override fun getResourceLayoutId() = R.layout.fragment_sounds

    override fun onInitDataBinding() {
        setupCardStyle()
        soundsAdapter.submitList(viewModel.cardList)
    }

    private fun setupCardStyle() {
        viewBinding.rvDog.apply {
            adapter = soundsAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
        }

        viewModel.cardList.forEach { card ->
            card.isPremium = when {
                card.premiumPhoto == R.drawable.ic_free -> true
                hasAccessToSound(card.mp3Title) -> true // Süresi dolmamışsa premium olarak kabul et
                else -> false // Diğer durumlar için premium değil
            }
        }

        soundsAdapter.submitList(viewModel.cardList)
    }

    private fun loadInterAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireContext(), INTERSTITIAL_ID, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
    }

    private fun showVideoAd(onAdCompleted: () -> Unit) {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                onAdCompleted()
                mInterstitialAd = null
                loadInterAd()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                mInterstitialAd = null
                loadInterAd()
            }

            override fun onAdShowedFullScreenContent() {
                mInterstitialAd = null
            }
        }
        mInterstitialAd?.show(requireActivity())
    }

    private fun hasAccessToSound(mp3Title: Int): Boolean {
        val currentTime = System.currentTimeMillis()
        val sharedPreferences = requireContext().getSharedPreferences("sound_prefs", Context.MODE_PRIVATE)
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
        card?.apply {
            isPremium = true
            premiumPhoto = R.drawable.ic_free
        }
        soundsAdapter.notifyDataSetChanged()

        Handler(Looper.getMainLooper()).postDelayed({
            card?.apply {
                isPremium = false
                premiumPhoto = R.drawable.ic_advert
            }
            soundsAdapter.notifyDataSetChanged()
        }, 24 * 60 * 60 * 1000)
    }

    private fun saveExpirationTimeForSound(mp3Title: Int, expirationTime: Long) {
        val sharedPreferences = requireContext().getSharedPreferences("sound_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putLong("sound_$mp3Title", expirationTime).apply()
    }

    private fun playSound(mp3Title: Int) {
        val card = viewModel.cardList.find { it.mp3Title == mp3Title }
        card?.let {
            // Sadece sesi çal
            mediaPlayer?.apply {
                if (isPlaying) {
                    stop()
                }
                release()
            }

            mediaPlayer = MediaPlayer.create(requireContext(), mp3Title)
            mediaPlayer?.start()
        }
    }

    private fun navigateToSubscriptionScreen() {
        findNavController().navigate(R.id.action_soundsFragment_to_premiumScreenPurchaseFragment)
    }

    override fun onItemClick(item: CardModel) {
        selectedItemPosition = viewModel.cardList.indexOf(item)
        selectedCard = item
        soundsAdapter.notifyItemChanged(selectedItemPosition)

        when {
            item.isPremium -> playSound(item.mp3Title)
            item.premiumPhoto == R.drawable.ic_advert -> showAdForSound(item)
            item.premiumPhoto == R.drawable.ic_free -> {
                // Eğer kart zaten free ise, sadece sesi çal
                playSound(item.mp3Title)
            }
            item.premiumPhoto == R.drawable.ic_premium -> navigateToSubscriptionScreen()
        }
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