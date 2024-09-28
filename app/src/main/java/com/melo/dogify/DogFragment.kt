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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Play a random dog sound when the fragment is initialized
        playRandomDogSound()
    }

    private fun playRandomDogSound() {
        // Create a list of sound resource IDs
        val soundResIds = listOf(
            R.raw.sound_anxious_dog, // Add your actual sound file names
            R.raw.sound_call_dog,
            R.raw.sound_feeding_dog
            // Add more sounds as needed
        )

        // Generate a random index to select a sound
        val randomSoundIndex = Random.nextInt(soundResIds.size)

        // Get the selected sound resource ID
        val soundResId = soundResIds[randomSoundIndex]

        // Play the sound
        mediaPlayer = MediaPlayer.create(requireContext(), soundResId)
        mediaPlayer?.start()
        mediaPlayer?.setOnCompletionListener {
            // Release the MediaPlayer when done
            it.release()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the MediaPlayer if it is not null
        mediaPlayer?.release()
        mediaPlayer = null
        _binding = null // Clear binding reference
    }
}
