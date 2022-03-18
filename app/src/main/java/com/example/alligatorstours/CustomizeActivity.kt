package com.example.alligatorstours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout

class CustomizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize)

        val submitBtn = findViewById<Button>(R.id.btnSubmit)

        submitBtn.setOnClickListener {

            val inputArray = arrayListOf<String>()
            val list = findViewById<LinearLayout>(R.id.inputLayout)

            Log.d("", "LinearLayout has ${list.childCount} children")

            val count = list.childCount - 1
            for (i in 0..count) {
                val cb = list.getChildAt(i) as CheckBox
                if (cb.isChecked) {
                    Log.d("Add", "Added ${cb.text.toString()} to the list...")
                    inputArray.add(cb.text.toString())
                }
            }

            Intent(this, TourActivity::class.java).also {
                it.putExtra("EXTRA_LOCATIONS", inputArray)
                startActivity(it)
            }
        }

    }
}