package com.example.alligatorstours.chatbot.ui

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alligatorstours.BuildConfig
import com.example.alligatorstours.R
import com.example.alligatorstours.chatbot.cca2client.CCA2Client
import com.example.alligatorstours.chatbot.data.Message
import com.example.alligatorstours.chatbot.utils.Constants.RECEIVE_ID
import com.example.alligatorstours.chatbot.utils.Constants.SEND_ID
import com.example.alligatorstours.chatbot.utils.Time
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.*
import java.util.*


class ChatActivity : AppCompatActivity() {
    var cca2ContextPath = BuildConfig.CCA_CONTEXT_PATH
    var cca2Auth = BuildConfig.CCA_AUTH
    var cogbotId = BuildConfig.COGBOT_ID
    var language = "en-us"
    var country = "us"

    private lateinit var adapter: MessagingAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val textView = findViewById<TextView>(R.id.tv_text)

        recyclerView()

        clickEvents()
        customMessage("Hello! Today you're speaking with Alli. How may I help?")



        btn_speech.setOnClickListener(){
            askSpeechInput()
            callClient("Who are you?", textView)
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

        val cca2Client =
            CCA2Client(
                cca2ContextPath,
                cca2Auth,
                cogbotId,
                language,
                country
            )

        val messageResponse: List<List<String>> = cca2Client.callMessageApi(input)
        view.text = ""
        for (list in messageResponse) {
            for (item in list) {
                println(item)
                view.append(Html.fromHtml(item, 0, null, null))
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            // TODO: get message from chatbot

        }
    }

    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}