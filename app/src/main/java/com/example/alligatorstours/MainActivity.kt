package com.example.alligatorstours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toCommonTours = findViewById<Button>(R.id.btnToCommonTours)
        toCommonTours.setOnClickListener {
            Intent(this, CommonToursActivity::class.java).also {
                startActivity(it)
            }
        }
        val toCustomize = findViewById<Button>(R.id.btnCustomize)
        toCustomize.setOnClickListener {
            Intent(this, CustomizeActivity::class.java).also {
                startActivity(it)
            }
        }

        floatingActionButton.setOnClickListener{
            Intent(this,ChatActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}