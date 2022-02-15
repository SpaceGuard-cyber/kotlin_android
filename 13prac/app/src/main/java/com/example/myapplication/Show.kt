package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.Serializable
import kotlin.collections.List as List


// осуществление расшифровки
class Find_Showman  (
    private val newName:String?,
    private val newGenre:List<String>?,
    private val newLanguage:String?
):Serializable {
    @SerializedName("name")
    @Expose
    var name:String? = null

    @SerializedName("genres")
    @Expose
    var genre:List<String>? = null

    @SerializedName("language")
    @Expose
    var language:String? = null
}

class Show(private val newList : List<Find_Showman>?) :Serializable {

    @SerializedName("show")
    @Expose
    var show: Find_Showman? = null
}

// запросы которые можно осуществлять
interface APIShowmanName{
    @GET("search/shows?")
    fun getShowmanByName(@Query("q") name: String): Call<List<Show>>
}