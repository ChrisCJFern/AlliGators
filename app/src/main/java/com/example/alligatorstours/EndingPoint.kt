package com.example.alligatorstours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class EndingPoint : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ending_point)

        var listView = findViewById<ListView>(R.id.endinglistview)
        var list = ArrayList<String>()
        list.add("Reitz Union")
        list.add("Plaza of the Americas")
        list.add("Century Tower")
        list.add("Career Connections Center")
        list.add("College of Journalism")
        list.add("Marston Science Library")
        list.add("Ben Hill Griffin Stadium")
        list.add("The Hub")
        list.add("The Potato (Turlington Rock)")
        list.add("The French Fries")
        list.add("Tigert Hall")
        list.add("Field and Fork")
        list.add("Criser Hall")
        list.add("Broward Hall")
        list.add("Stephen C. O'Connell Center")

        val arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, list)
        listView.adapter = arrayAdapter

        val origin = intent.extras!!.getString("ORIGIN")

        listView.setOnItemClickListener{ parent, view, position, id ->
            Intent(this, CustomizeActivity::class.java).also {
                it.putExtra("DESTINATION", list[position])
                it.putExtra("ORIGIN", origin)
                startActivity(it)
            }
        }

    }
}