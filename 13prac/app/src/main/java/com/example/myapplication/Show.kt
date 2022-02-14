package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.Serializable
import kotlin.collections.List as List


// осуществление расшифровки
data class Find_Showman(
    @SerializedName("name")
    @Expose
    var name:String? = null,

    @SerializedName("genre")
    @Expose
    var genre:List<String>? = null,

    @SerializedName("language")
    @Expose
    var language:String? = null
):Serializable

data class Show (
    @SerializedName("show")
    @Expose
    var showList : List<Find_Showman>? = null
):Serializable

// запросы которые можно осуществлять
public interface APIShowmanName{
    @GET("search/shows?")
    fun getShowmanByName(@Query("q") name: String): Call<List<Show>>
}