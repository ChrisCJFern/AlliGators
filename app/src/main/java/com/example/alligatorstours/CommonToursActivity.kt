package com.example.alligatorstours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CommonToursActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_tours)

        val toMap = findViewById<Button>(R.id.btnMap)
        toMap.setOnClickListener {
            Intent(this, TourActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}