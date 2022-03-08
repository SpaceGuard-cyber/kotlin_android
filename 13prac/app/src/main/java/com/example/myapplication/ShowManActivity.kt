package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import org.w3c.dom.Text
import java.util.jar.Attributes

class ShowManActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_show_man)
        val binding:ShowManActivityBindingImpl = DataBindingUtil.setContentView(this, R.layout.activity_show_man)
        val nameViewModel = intent.getSerializableExtra("Showman") as NameViewModel
        binding.nameViewModel = nameViewModel
/*        findViewById<TextView>(R.id.nameShowman).text = name.name
        findViewById<TextView>(R.id.languageShowman).text = name.language
        findViewById<TextView>(R.id.genreShowman).text = name.genre.toString()*/
    }
}