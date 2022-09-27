package com.example.alligatorstours

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.text.Html
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.alligatorstours.cca2client.CCA2Client
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*


class ChatActivity : AppCompatActivity() {
    var cca2ContextPath = BuildConfig.CCA_CONTEXT_PATH
    var cca2Auth = BuildConfig.CCA_AUTH
    var cogbotId = BuildConfig.COGBOT_ID
    var language = "en-us"
    var country = "us"

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val textView = findViewById<TextView>(R.id.tv_text)

        callClient("Hey Alli, where were you born?", textView)

        btn_speech.setOnClickListener(){
            askSpeechInput()
        }
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if(it.resultCode == Activity.RESULT_OK){
                val value = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                tv_text.text = value?.get(0).toString()
            }
        }

    private fun askSpeechInput(){
        if(!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText(this, "Speech recognition is not available", Toast.LENGTH_SHORT).show()
        }
        else{
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Talk to Alli!")
            getResult.launch(i)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun callClient(input: String, view: TextView) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val cca2Client = CCA2Client(cca2ContextPath, cca2Auth, cogbotId, language, country)

        val messageResponse: List<List<String>> = cca2Client.callMessageApi(input)
        view.text = ""
        for (list in messageResponse) {
            for (item in list) {
                println(item)
                view.append(Html.fromHtml(item, 0, null, null))
            }
        }
    }
}