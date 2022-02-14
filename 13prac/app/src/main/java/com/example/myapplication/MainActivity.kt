package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class AdapterShowMan(val name:Show, val mainActivity: MainActivity):RecyclerView.Adapter<AdapterShowMan.ShowmanHolder>() {
    class ShowmanHolder (val view: ViewGroup) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowmanHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_man_item, parent,false) as ViewGroup
        return ShowmanHolder(view)
    }

    override fun onBindViewHolder(holder: ShowmanHolder, position: Int) {
        val showman = name.showList!![position]
        holder.view.findViewById<TextView>(R.id.name).text = showman.name
        holder.view.findViewById<TextView>(R.id.language).text = showman.language
        holder.view.findViewById<TextView>(R.id.genre).text = showman.genre!!.joinToString(",")
        holder.view.setOnClickListener {
            val intent = Intent(mainActivity, ShowManActivity::class.java)
            intent.putExtra("Showman", showman)
            mainActivity.startActivity(intent)
        }
    }
    override fun getItemCount() = name.showList!!.size
}



/*
data class Name_Showmen(
    var name: String? = null
)

//retrofit - работа с сайтами
//room - работа с базами данных

public interface APIShowMen{
    @GET("/{Name}/")
    fun Find_ShowMen(@Path("Name_Showmen") Name_Showmen:String): Call<List<ShowmanItem>>
}*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outputName = findViewById<RecyclerView>(R.id.outputName)
        val db = Room.databaseBuilder(applicationContext,ShowmanNameDataBase::class.java, "ShowmanName").build()

        CoroutineScope(Dispatchers.Main).launch{
            var listDataBase: List<DataBaseShowMan>? = null
            withContext(Dispatchers.IO){
                listDataBase = db.showmanNameDao().getAll()
            }
            val listDataBaseForOutPutName = Show(
                showList = listDataBase!!.map {
                    Find_Showman(
                        name = it.name,
                        genre = listOf(it.genre),
                        language = it.language
                    )
            })
            outputName.adapter = AdapterShowMan(listDataBaseForOutPutName, this@MainActivity)
            outputName.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        //Json
        //XML

        //val api = retrofit.create(APIShowMen::class.java) // запрос интерфейса

        findViewById<Button>(R.id.button).setOnClickListener {

            val inputName = findViewById<EditText>(R.id.inputName).text.toString()

            // классический поток
            // создается объект класса Thread туда передается runnable внутри runnable функция start
            // создание объекта retrofit
            val retrofit = Retrofit.Builder().baseUrl("https://api.tvmaze.com/").addConverterFactory(GsonConverterFactory.create()).build()

            // получение объекта для инкапсуляции работы с интерфейсом
            // создание объекста для интерфейса который описан
            val serviceName = retrofit.create(APIShowmanName::class.java)

            // получение объекта для вызова который единственный
            val callName = serviceName.getShowmanByName(inputName)

            // осуществление вызова
            // Callback - содержит функции, когда запросы успешно выполнены
            // onResponse - успешно выполнено
            // onFailure - неуспешно выполнено
            // их надо реализовать
            callName.enqueue(object : Callback<List<Show>> {
                override fun onResponse(call: Call<List<Show>>, response: Response<List<Show>>) {
                    // выполнять внутри потока связанного с интерфейсом
                    // метод post - передается лямда функция которая будет выполнена в том потоке в который связан с вводом/выводом
                    // этот метод легче чем корутины!
                    //outputName.post {
                    Toast.makeText(this@MainActivity, "", Toast.LENGTH_LONG).show()
                    val listName = response.body()!!
                        CoroutineScope(Dispatchers.IO).launch {
                            //db.showmanNameDao().deleteAll()
                            //db.showmanNameDao().insertAll(listName.showList!!.map{
                            //    DataBaseShowMan(it.name!!, it.genre!!.joinToString(","), it.language!!)
                            //})
                        }
                    //}
                }

                override fun onFailure(call: Call<List<Show>>, t: Throwable) {
                    //outputName.post {
                        Toast.makeText(this@MainActivity, getString(R.string.error) + t.localizedMessage, Toast.LENGTH_LONG).show()
                    //}
                }
            })
        }
    }
}