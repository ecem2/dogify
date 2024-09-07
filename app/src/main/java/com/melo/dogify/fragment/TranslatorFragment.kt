package com.melo.dogify.fragment

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
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

    private var mediaRecorder: MediaRecorder? = null
    private val REQUEST_RECORD_AUDIO_PERMISSION = 200
    private var permissionToRecordAccepted = false
    private val permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    private var tts: TextToSpeech? = null

    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_translator

    override fun onInitDataBinding() {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
            } else {
                Log.e("ecemm", "TTS initialization failed.")
            }
        }
        viewBinding.sound.setOnClickListener {
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
                startRecordingProcess()
            }
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
        }
        viewBinding.tick.setOnClickListener {
            stopRecording()
            stopRecordingProcess()
            processRecordedSound()
            playDogSound()
        }
    }

    private fun processRecordedSound() {
        val jsonString = context?.let { loadJsonFromAssets(it, "dog_words.json") }
        val gson = Gson()
        val dogSounds: List<DogSound> = gson.fromJson(jsonString, Array<DogSound>::class.java).toList()

        val randomIndex = Random.nextInt(dogSounds.size)
        val randomWord = dogSounds[randomIndex].word
        val foundSound = dogSounds.find { it.word.equals(randomWord, ignoreCase = true) }

        if (foundSound != null) {
            Log.d("ecemm", "Word: ${foundSound.word}")
            Log.d("ecemm", "Meaning: ${foundSound.meaning}")
            Log.d("ecemm", "Turkish: ${foundSound.turkish}")

            matchTurkishPhraseAndPlaySound(foundSound.turkish)
        } else {
            Log.d("ecemm", "Ses bulunamadı.")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            permissionToRecordAccepted = grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED

            if (permissionToRecordAccepted) {
                startRecordingProcess()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Ses kaydetme izni gerekli!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun startRecordingProcess() {
        viewBinding.sound.visibility = View.GONE
        viewBinding.exit.visibility = View.VISIBLE
        viewBinding.pause.visibility = View.VISIBLE
        viewBinding.tick.visibility = View.VISIBLE

        startRecording()


       // stopRecording()

//        val jsonString = context?.let { loadJsonFromAssets(it, "dog_words.json") }
//        val gson = Gson()
//        val dogSounds: List<DogSound> = gson.fromJson(jsonString, Array<DogSound>::class.java).toList()
//
//        val randomIndex = Random.nextInt(dogSounds.size)
//        val randomWord = dogSounds[randomIndex].word
//        val foundSound = dogSounds.find { it.word.equals(randomWord, ignoreCase = true) }
//
//        if (foundSound != null) {
//            Log.d("ecemm", "Word: ${foundSound.word}")
//            Log.d("ecemm", "Meaning: ${foundSound.meaning}")
//            Log.d("ecemm", "Turkish: ${foundSound.turkish}")
//
//        } else {
//            Log.d("ecemm", "Ses bulunamadı.")
//        }
    }
    private fun stopRecordingProcess() {
        stopRecording()
        processRecordedSound()
    }
    private fun startRecording() {
        try {
            mediaRecorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(context?.externalCacheDir?.absolutePath + "/audiorecordtest.3gp")
                prepare()
                start()
            }
            Log.d("ecemm", "Recording started")
        } catch (e: Exception) {
            Log.e("ecemm", "Recording failed to start: ${e.message}")
        }
    }

    private fun playDogSound(word: String) {
        tts?.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        tts?.stop()
        tts?.shutdown()
    }

    private fun generateSoundFileFromWord(word: String) {
        val filePath = context?.externalCacheDir?.absolutePath + "/${word}.wav"
        val file = File(filePath)

        tts?.synthesizeToFile(word, null, file, word) // Text'i dosyaya çeviriyoruz

        // Daha sonra bu dosyayı çalabilirsiniz:
        playGeneratedSound(filePath)
    }

    private fun playGeneratedSound(filePath: String) {
        val mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(filePath)
                prepare()
                start()
            } catch (e: Exception) {
                Log.e("ecemm", "Sound playback failed: ${e.message}")
            }
        }
    }

    fun matchTurkishPhraseAndPlaySound(turkishPhrase: String) {
        val jsonString = context?.let { loadJsonFromAssets(it, "dog_words.json") }
        val gson = Gson()
        val dogSounds: List<DogSound> = gson.fromJson(jsonString, Array<DogSound>::class.java).toList()

        // "turkish" ile eşleşen kayıt bulunuyor
        val foundSound = dogSounds.find { it.turkish.equals(turkishPhrase, ignoreCase = true) }

        if (foundSound != null) {
            Log.d("ecemm", "Eşleşen kelime: ${foundSound.word}")
            playDogSound(foundSound.word) // Kelimeyi seslendiriyoruz
        } else {
            Log.d("ecemm", "Eşleşen ses bulunamadı.")
        }
    }


    private fun stopRecording() {
        try {
            mediaRecorder?.apply {
                stop()
                release()
            }
            mediaRecorder = null
            Log.d("ecemm", "Recording stopped")
        } catch (e: RuntimeException) {
            Log.e("ecemm", "stopRecording failed: ${e.message}")
        }
    }
    private fun playDogSound() {
        val recordedFilePath = context?.externalCacheDir?.absolutePath + "/audiorecordtest.3gp"
        val mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(recordedFilePath)
                prepare()
                start()
            } catch (e: Exception) {
                Log.e("ecemm", "Dog sound playback failed: ${e.message}")
            }
        }
    }

    fun loadJsonFromAssets(context: Context, fileName: String): String? {
        val json: String?
        try {
            val inputStream = context.assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}
