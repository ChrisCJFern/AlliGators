package com.example.alligatorstours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

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
    }

}