package com.melo.dogify.fragment


import android.media.MediaPlayer
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.melo.dogify.R
import com.melo.dogify.adapter.SoundsAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentSoundsBinding
import com.melo.dogify.model.CardModel
import com.melo.dogify.viewmodel. SoundsViewModel
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
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.dog_sound)
            Log.d("ecemm", "$mediaPlayer")

            if (mediaPlayer == null) {
                Log.d("ecemm", "MediaPlayer null, dosya bulunamadı!")
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
    }


    override fun onItemClick(item: CardModel) {
        val previousPosition = selectedItemPosition
        selectedItemPosition = viewModel.cardList.indexOf(item)
        selectedCard = item

        // Sadece değişiklik olan item'ları güncelle
        soundsAdapter.notifyItemChanged(previousPosition)
        soundsAdapter.notifyItemChanged(selectedItemPosition)

        playSound()
    }


}
