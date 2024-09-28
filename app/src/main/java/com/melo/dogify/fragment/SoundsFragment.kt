package com.melo.dogify.fragment


import android.media.MediaPlayer
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.melo.dogify.R
import com.melo.dogify.adapter.SoundsAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentSoundsBinding
import com.melo.dogify.model.CardModel
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint


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
        soundsAdapter.submitList(viewModel.cardList)

    }

    override fun onResume() {
        super.onResume()
    }

    private fun playSound() {
        selectedCard?.let { card ->
            try {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(requireContext(), card.mp3Title)
                Log.d("ecemm", "Playing sound: ${card.text}")

                if (mediaPlayer == null) {
                    Log.d("ecemm", "MediaPlayer null, sound file not found!")
                    return
                }
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } ?: run {
            Log.d("ecemm", "No card selected!")
        }
    }


    override fun onItemClick(item: CardModel) {
        val previousPosition = selectedItemPosition
        selectedItemPosition = viewModel.cardList.indexOf(item)
        selectedCard = item

        soundsAdapter.notifyItemChanged(previousPosition)
        soundsAdapter.notifyItemChanged(selectedItemPosition)

        playSound()
    }


}
