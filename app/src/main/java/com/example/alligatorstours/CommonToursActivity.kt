package com.example.alligatorstours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CommonToursActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_tours)

        val waypointsArray = arrayListOf<String>()
        var origin = ""
        var destination = ""

        val toMap = findViewById<Button>(R.id.generalTour)
        toMap.setOnClickListener {
            origin = "Stephen C. O'Connell Center"
            destination = "Tigert Hall"
            waypointsArray.clear()
            waypointsArray.addAll(mutableListOf("Ben Hill Griffin Stadium","Reitz Union",
                "Career Connections Center","College of Journalism","The Hub","Field and Fork",
                "The French Fries","Marston Science Library","The Potato (Turlington Rock)",
                "Century Tower","Plaza of the Americas","Criser Hall","Broward Hall"))

            Intent(this, TourActivity::class.java).also {
                it.putExtra("EXTRA_LOCATIONS", waypointsArray)
                it.putExtra("ORIGIN", origin)
                it.putExtra("DESTINATION", destination)
                startActivity(it)
            }
        }

        val toHist = findViewById<Button>(R.id.historicalTour)
        toHist.setOnClickListener {
            origin = "Murphree Hall"
            destination = "Smathers Library"
            waypointsArray.clear()
            waypointsArray.addAll(mutableListOf("Infirmary","Newell Hall","University Auditorium",
                "Peabody Hall"))

            Intent(this, TourActivity::class.java).also {
                it.putExtra("EXTRA_LOCATIONS", waypointsArray)
                it.putExtra("ORIGIN", origin)
                it.putExtra("DESTINATION", destination)
                startActivity(it)
            }
        }

        val toEng = findViewById<Button>(R.id.engineeringTour)
        toEng.setOnClickListener {
            origin = "Weil Hall"
            destination = "New Engineering Building"
            waypointsArray.clear()
            waypointsArray.addAll(mutableListOf("Nuclear Sciences Building","Herbert Wertheim Laboratory",
                "Reitz Union","Career Connections Center","Frazier-Rogers Hall","Larsen Hall","Benton Hall"))

            Intent(this, TourActivity::class.java).also {
                it.putExtra("EXTRA_LOCATIONS", waypointsArray)
                it.putExtra("ORIGIN", origin)
                it.putExtra("DESTINATION", destination)
                startActivity(it)
            }
        }

//        val toLibSci = findViewById<Button>(R.id.LAnSTour)
//        toLibSci.setOnClickListener {
//            origin = ""
//            destination = ""
//            waypointsArray.clear()
//            waypointsArray.addAll(mutableListOf(""))
//
//            Intent(this, TourActivity::class.java).also {
//                it.putExtra("EXTRA_LOCATIONS", waypointsArray)
//                it.putExtra("ORIGIN", origin)
//                it.putExtra("DESTINATION", destination)
//                startActivity(it)
//            }
//        }

    }
}