package com.melo.dogify

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.melo.dogify.databinding.FragmentDogBinding
import kotlin.random.Random

class DogFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    private var _binding: FragmentDogBinding? = null
    private val binding get() = _binding!!
    private var lastPlayedSoundResId: Int? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playRandomDogSound()
        binding.dogBtn.setOnClickListener {
            lastPlayedSoundResId?.let { soundResId ->
                playSound(soundResId)
            }
        }
        binding.dogBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun playRandomDogSound() {
        val rawFields = R.raw::class.java.fields
        val soundResIds = rawFields.mapNotNull { field ->
            try {
                field.getInt(null)
            } catch (e: Exception) {
                Log.e("DogFragment", "Error getting resource id", e)
                null
            }
        }

        val randomSoundIndex = Random.nextInt(soundResIds.size)
        val soundResId = soundResIds[randomSoundIndex]

        lastPlayedSoundResId = soundResId
        playSound(soundResId)
    }

    private fun playSound(soundResId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(requireContext(), soundResId)
        mediaPlayer?.start()
        mediaPlayer?.setOnCompletionListener {
            it.release()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
        _binding = null
    }
}
