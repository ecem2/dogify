package com.melo.dogify.fragment

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.melo.dogify.R
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentTranslatorBinding
import com.melo.dogify.model.DogSound
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.Locale
import kotlin.random.Random

@Suppress("DEPRECATION")
@AndroidEntryPoint
class TranslatorFragment : BaseFragment<SoundsViewModel, FragmentTranslatorBinding>() {

    private val REQUEST_RECORD_AUDIO_PERMISSION = 200
    private val permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var speechIntent: Intent
    private lateinit var recognizedText: String
    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_translator

    override fun onInitDataBinding() {
        viewBinding.sound.setOnClickListener {
            translatorPp()  // Mikrofonu başlatan fonksiyonu çağır
            recognizedText = ""

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    permissions,
                    REQUEST_RECORD_AUDIO_PERMISSION
                )
            } else {
                speechRecognizer.startListening(speechIntent)
            }

            viewBinding.sound.visibility = View.GONE
            viewBinding.pause.visibility = View.VISIBLE
            viewBinding.exit.visibility = View.VISIBLE
            viewBinding.tick.visibility = View.VISIBLE
        }

        viewBinding.pause.setOnClickListener {
            viewBinding.sound.visibility = View.VISIBLE
            viewBinding.pause.visibility = View.GONE
            viewBinding.exit.visibility = View.GONE
            viewBinding.tick.visibility = View.GONE
        }

        viewBinding.exit.setOnClickListener {
            viewBinding.sound.visibility = View.VISIBLE
            viewBinding.pause.visibility = View.GONE
            viewBinding.exit.visibility = View.GONE
            viewBinding.tick.visibility = View.GONE
            viewBinding.txtRecord.text = ""
            recognizedText = ""

        }

        viewBinding.tick.setOnClickListener {
            viewBinding.txtRecord.text = recognizedText
            // Tanınan cümle varsa txtRecord TextView'ine yaz
//            if (recognizedText.isNotEmpty()) {
//                viewBinding.txtRecord.text = recognizedText
//            } else {
//                Toast.makeText(requireContext(), "Henüz bir cümle tanımlanmadı.", Toast.LENGTH_SHORT).show()
//            }
        }
    }


    fun translatorPp() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        // Burada Türkçe dilini belirtiyoruz
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "tr-TR")
        val isTranslatorOnLeft =
            viewBinding.translatorPp.layoutParams is ConstraintLayout.LayoutParams &&
                    (viewBinding.translatorPp.layoutParams as ConstraintLayout.LayoutParams).startToStart == ConstraintLayout.LayoutParams.PARENT_ID

        if (isTranslatorOnLeft) {
            viewBinding.translatorPp.setOnClickListener {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.RECORD_AUDIO
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    speechRecognizer.startListening(speechIntent)
                } else {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        permissions,
                        REQUEST_RECORD_AUDIO_PERMISSION
                    )
                }
            }
        }

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    recognizedText = matches[0]
                    Log.d("SpeechRecognition", "Recognized Text: $recognizedText")
                } else {
                    Log.d("SpeechRecognition", "No matches found.")
                }
            }

            override fun onError(error: Int) {
                Log.e("ecemm", "Error occurred: $error")
            }

            override fun onReadyForSpeech(params: Bundle?) {
                Log.d("ecemm", "Ready for speech.")
            }

            override fun onBeginningOfSpeech() {
                Log.d("ecemm", "Speech started.")
            }

            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {
                Log.d("ecemm", "Speech ended.")
            }

            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }


        // İzin sonucu burada kontrol edilecek
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // İzin verildiyse konuşmayı başlat
                speechRecognizer.startListening(speechIntent)
            } else {
                Toast.makeText(requireContext(), "Mikrofon izni reddedildi.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
