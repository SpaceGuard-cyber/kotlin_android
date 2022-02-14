package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text
import java.util.jar.Attributes

class ShowManActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_man)
        val name = intent.getSerializableExtra("Showman") as Find_Showman

        findViewById<TextView>(R.id.nameShowman).text = name.name
        findViewById<TextView>(R.id.language).text = name.language
        findViewById<TextView>(R.id.genre).text = name.genre.toString()
    }
}