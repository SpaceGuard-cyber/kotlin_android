package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit - работа с сайтами (REST)
//ROOM - база данных

public interface APIShowman{

}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //JSON
        //XML
        val retrofit = Retrofit.Builder().baseUrl("https://api.tvmaze.com").addConverterFactory(
            GsonConverterFactory.create()).build()
    }
}