package com.melo.dogify.fragment

import android.graphics.drawable.LayerDrawable
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.melo.dogify.R
import com.melo.dogify.adapter.FoodAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentFoodBinding
import com.melo.dogify.databinding.FragmentWhistleBinding
import com.melo.dogify.viewmodel.SoundsViewModel
import com.melo.dogify.viewmodel.WhistleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sin

@Suppress("DEPRECATION")
@AndroidEntryPoint

class WhistleFragment : BaseFragment<SoundsViewModel, FragmentWhistleBinding>() {


    private var duration = 3
    private var sampleRate = 8000
    private var freqOfTone =
        15000.0 // Köpeklerin duyabileceği en düşük frekans olarak başlatılıyor.

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var generatedSnd: ByteArray


    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_whistle

    override fun onInitDataBinding() {
        val layerDrawable = resources.getDrawable(R.drawable.custom_seekbar, null) as LayerDrawable
        viewBinding.seekBar.progressDrawable = layerDrawable

        viewBinding.seekBar.max = 30000 // Örnekleme oranını maksimum 44100 Hz olarak belirliyoruz.
        viewBinding.seekBar.progress = (freqOfTone - 15000).toInt()
        //viewBinding.seekBar.progress = sampleRate // SeekBar'ı başlangıç örnekleme oranına ayarlıyoruz.

        viewBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sampleRate =
                    progress // SeekBar'ın progress'i değiştikçe sampleRate'i güncelliyoruz.
                viewBinding.whistle.visibility = View.GONE
                viewBinding.playWhistle.visibility = View.VISIBLE

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                genTone()
                playSound()
            }
        })
    }

//    override fun onResume() {
//        super.onResume()
//
//        // Yeni bir thread kullanarak işlemi gerçekleştiriyoruz.
//        Thread {
//            genTone()
//            handler.post {
//                playSound()
//            }
//        }.start()
//    }

    private fun genTone() {
        val numSamples = duration * sampleRate
        val sample = DoubleArray(numSamples)
        generatedSnd = ByteArray(2 * numSamples)

        // Ses dalgasını oluşturuyoruz
        for (i in sample.indices) {
            sample[i] = sin(2.0 * Math.PI * i / (sampleRate / freqOfTone))
        }

        // Ses dalgasını PCM formatına çeviriyoruz
        var idx = 0
        for (dVal in sample) {
            val valShort = (dVal * 32767).toInt().toShort()
            generatedSnd[idx++] = (valShort.toInt() and 0x00ff).toByte()
            generatedSnd[idx++] = ((valShort.toInt() and 0xff00).ushr(8)).toByte()
        }
    }

    private fun playSound() {
        val audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            generatedSnd.size,
            AudioTrack.MODE_STATIC
        )
        audioTrack.write(generatedSnd, 0, generatedSnd.size)
        audioTrack.play()

        // Ses çalmayı bitirdikten sonra whistle'ı görünür, playWhistle'ı görünmez yap
        audioTrack.setNotificationMarkerPosition(generatedSnd.size / 2)
        audioTrack.setPlaybackPositionUpdateListener(object : AudioTrack.OnPlaybackPositionUpdateListener {
            override fun onMarkerReached(track: AudioTrack?) {
                viewBinding.whistle.visibility = View.VISIBLE
                viewBinding.playWhistle.visibility = View.GONE
            }

            override fun onPeriodicNotification(track: AudioTrack?) {}
        })
    }

}
