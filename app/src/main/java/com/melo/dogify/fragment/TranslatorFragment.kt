package com.melo.dogify.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.hariprasanths.bounceview.BounceView
import com.google.gson.Gson
import com.melo.dogify.R
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentTranslatorBinding
import com.melo.dogify.model.DogSound
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@Suppress("DEPRECATION")
@AndroidEntryPoint
class TranslatorFragment : BaseFragment<SoundsViewModel, FragmentTranslatorBinding>() {

    private val permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var speechIntent: Intent
    private var recognizedText: String? = null
    private var switchActive = false
    private var lastRandomWord: String? = null


    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_translator

    override fun onInitDataBinding() {
        with(viewBinding) {
            BounceView.addAnimTo(leftRight)

            sound.setOnClickListener {
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
                    setupListening()
                }
            }

            pause.setOnClickListener {
                sound.visibility = View.VISIBLE
                pause.visibility = View.GONE
                exit.visibility = View.GONE
                tick.visibility = View.GONE
            }

            exit.setOnClickListener {
                disableButtons()
                txtRecord.text = ""
                recognizedText = null
            }

            leftRight.setOnClickListener {
                disableButtons()
                txtRecord.text = null
                switchTranslatorPositions()
            }

            tick.setOnClickListener {
                // disableButtons()
                checkTranslator()

            }
        }
    }


    private fun checkTranslator() {
        if (!switchActive) {
            // Burada switchActive false olduğunda tanınan kelimeyi göster
            viewBinding.txtRecord.text = recognizedText
            viewBinding.txtRecord.postDelayed({
                navigateToDogFragment()
            }, 2000)
        }
    }


    private fun navigateToDogFragment() {
        lifecycleScope.launch {
            findNavController().navigate(R.id.action_translatorFragment_to_dogFragment)
        }
    }

    private fun switchTranslatorPositions() {
        with(viewBinding) {
            if (switchActive) {
                switchActive = false
                translatorPp.setImageResource(R.drawable.translator_pp)
                translatorDogPp.setImageResource(R.drawable.translator_dog_pp)
            } else {
                switchActive = true
                translatorPp.setImageResource(R.drawable.translator_dog_pp)
                translatorDogPp.setImageResource(R.drawable.translator_pp)
            }
        }
    }

    private fun loadDogWords(): List<DogSound> {
        return try {
            val inputStream = requireContext().assets.open("dog_words.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val json = String(buffer, Charsets.UTF_8)
            Gson().fromJson(json, Array<DogSound>::class.java).toList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun getRandomTurkishWord(): String {
        val dogWords = loadDogWords()
        return if (dogWords.isNotEmpty()) {
            val randomWord = dogWords.random()
            randomWord.meaning
        } else {
            "Kelime bulunamadı."
        }
    }

    private fun setupSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    if (switchActive) {
                        val randomWord = getRandomTurkishWord()
                        if (viewBinding.txtRecord.text != randomWord) {
                            viewBinding.txtRecord.text = randomWord
                            lastRandomWord = randomWord
                        }
                        recognizedText = null
                    } else {
                        recognizedText = matches[0]
                        Log.d("SpeechRecognition", "Recognized Text: $recognizedText")
                        viewBinding.txtRecord.text = recognizedText
                        lastRandomWord = null
                    }
                }
            }


            override fun onError(error: Int) {
                Log.e("SpeechRecognition", "Error occurred: $error")
            }

            override fun onReadyForSpeech(params: Bundle?) {
                Log.d("SpeechRecognition", "Ready for speech.")
            }

            override fun onBeginningOfSpeech() {
                Log.d("SpeechRecognition", "Speech started.")
            }

            override fun onEndOfSpeech() {
                speechRecognizer.stopListening()
            }

            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }


    private fun activateButtons() {
        with(viewBinding) {
            sound.visibility = View.GONE
            pause.visibility = View.VISIBLE
            exit.visibility = View.VISIBLE
            tick.visibility = View.VISIBLE
        }
    }

    private fun disableButtons() {
        with(viewBinding) {
            sound.visibility = View.VISIBLE
            pause.visibility = View.GONE
            exit.visibility = View.GONE
            tick.visibility = View.GONE
        }
    }

    private fun setupListening() {
        activateButtons()
        recognizedText = null
        viewBinding.txtRecord.text = ""
        setupSpeechRecognizer()
        speechRecognizer.startListening(speechIntent)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        if (requestCode == Companion.REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupListening()
            }
        }
    }

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 200
    }
}
