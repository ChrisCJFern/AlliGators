package com.example.alligatorstours

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.alligatorstours.chatbot.ui.ChatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // requesting permssions TODO: Move somewhere better later
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_SCAN), 1)
//        ActivityCompat.requestPermissions(this,
//            arrayOf(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ),2)

        val toCommonTours = findViewById<Button>(R.id.btnToCommonTours)
        toCommonTours.setOnClickListener {
            Intent(this, CommonToursActivity::class.java).also {
                startActivity(it)
            }
        }
        val toCustomize = findViewById<Button>(R.id.btnCustomize)
        toCustomize.setOnClickListener {
            Intent(this, StartingPoint::class.java).also {
                startActivity(it)
            }
        }

        floatingActionButton.setOnClickListener{
            Intent(this, ChatActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}