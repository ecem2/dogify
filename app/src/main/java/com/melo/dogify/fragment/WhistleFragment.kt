package com.melo.dogify.fragment

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import com.melo.dogify.R
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentWhistleBinding
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sin

@Suppress("DEPRECATION")
@AndroidEntryPoint

class WhistleFragment : BaseFragment<SoundsViewModel, FragmentWhistleBinding>() {

    private val minHz = 1
    private val maxHz = 20000
    private var isPlaying = false
    private lateinit var audioTrack: AudioTrack
    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_whistle

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onInitDataBinding() {

        viewBinding.seekBar.max = maxHz - minHz
        val initialHz = 10000
        viewBinding.seekBar.progress = initialHz - minHz
        viewBinding.textWhistle.text = "$initialHz Hz"

        viewBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val currentHz = progress + minHz
                viewBinding.textWhistle.text = "$currentHz Hz"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        viewBinding.whistle.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val currentHz = viewBinding.seekBar.progress + minHz
                    playWhistle(currentHz)
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    stopWhistle()
                }
            }
            true
        }
    }

    private fun playWhistle(hertz: Int) {
        if (isPlaying) return

        val sampleRate = 44100
        val generatedSound = DoubleArray(sampleRate)
        val buffer = ShortArray(sampleRate)

        for (i in 0 until sampleRate) {
            generatedSound[i] = sin(2.0 * Math.PI * i / (sampleRate / hertz))
            buffer[i] = (generatedSound[i] * Short.MAX_VALUE).toInt().toShort()
        }

        audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            buffer.size * 2,
            AudioTrack.MODE_STATIC
        )

        audioTrack.write(buffer, 0, buffer.size)
        audioTrack.play()
        isPlaying = true

        viewBinding.whistle.visibility = View.GONE
        viewBinding.playWhistle.visibility = View.VISIBLE
    }

    private fun stopWhistle() {
        if (!isPlaying) return
        audioTrack.stop()
        audioTrack.release()
        isPlaying = false

        viewBinding.whistle.visibility = View.VISIBLE
        viewBinding.playWhistle.visibility = View.GONE
    }

}
