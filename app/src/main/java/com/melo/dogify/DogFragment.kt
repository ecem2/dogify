package com.melo.dogify

import android.content.res.Resources
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
        playRandomDogSound()
    }

    private fun playRandomDogSound() {
        // raw klasöründen tüm ses dosyalarını almak için refleksiyon kullanıyoruz
        val rawFields = R.raw::class.java.fields
        val soundResIds = rawFields.mapNotNull { field ->
            try {
                field.getInt(null) // raw dosyasındaki kaynak id'lerini alıyoruz
            } catch (e: Exception) {
                Log.e("DogFragment", "Error getting resource id", e)
                null
            }
        }

        // Rastgele bir ses dosyasını seçip çalıyoruz
        val randomSoundIndex = Random.nextInt(soundResIds.size)
        val soundResId = soundResIds[randomSoundIndex]

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
