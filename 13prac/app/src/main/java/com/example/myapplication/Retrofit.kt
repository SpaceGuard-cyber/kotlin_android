package com.example.myapplication

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Names_Internet{
    interface Result
    data class ResultOK(val show: List<Show>):Result
    data class ResultFail(val message:String):Result

    fun getNamesByShows(name: String, f:(Result)->Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val serviceName = retrofit.create(APIShowmanName::class.java)
        // получение объекта для вызова который единственный
        val callName = serviceName.getShowmanByName(inputName)
        callName.enqueue(object : Callback<List<Show>> {
            override fun onResponse(call: Call<List<Show>>, response: Response<List<Show>>) {
                outputName.post {
                    val listName = response.body()!!
                    f(ResultOK(listName))
                }
            }

            override fun onFailure(call: Call<List<Show>>, t: Throwable) {
                f(ResultFail(t.localizedMessage))
            }
        })
    }
}