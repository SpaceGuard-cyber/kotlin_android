package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.show_man_item.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myapplication.Names_Internet.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdapterShowMan(val name:Show, val mainActivity: MainActivity):RecyclerView.Adapter<AdapterShowMan.ShowmanHolder>() {
    class ShowmanHolder (val binding: NameBinding) : RecyclerView.ViewHolder(binding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowmanHolder {
        val binding:NameBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.show_man_item, null,false)

        //val view = LayoutInflater.from(parent.context).inflate(R.layout.show_man_item, parent,false) as ViewGroup
        return ShowmanHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowmanHolder, position: Int) {
        val showman = name.show
        val nameViewModel = NameViewModel(name)
        holder.binding.nameViewModel = nameViewModel
        //holder.view.findViewById<TextView>(R.id.name).text = showman.name
        //holder.view.findViewById<TextView>(R.id.language).text = showman.language
        //holder.view.findViewById<TextView>(R.id.genre).text = showman.genre!!.joinToString(",")
        holder.binding.root.setOnClickListener {
            val intent = Intent(mainActivity, ShowManActivity::class.java)
            intent.putExtra("Showman", NameViewModel)
            mainActivity.startActivity(intent)
        }
    }
    override fun getItemCount() =  0
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val outputName = findViewById<RecyclerView>(R.id.outputName)
        val namesRepository = Names_Repository(applicationContext)
        CoroutineScope(Dispatchers.Main).launch {
            val listDataBaseForOutPutName: Show
            withContext(Dispatchers.IO) {
                listDataBaseForOutPutName = namesRepository.getListShow()
            }

            outputName.adapter = AdapterShowMan(listDataBaseForOutPutName, this@MainActivity)
            outputName.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        findViewById<Button>(R.id.button).setOnClickListener {
            val inputName = findViewById<EditText>(R.id.inputName).text.toString()
            val namesInternet = Names_Internet()
            val db = Room.databaseBuilder(applicationContext, ShowmanNameDataBase::class.java, "ShowmanName").build()
            namesInternet.getNamesByShows(name.toString()) {
                when (it) {
                    is Names_Internet.ResultOK -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            namesRepository.saveToDB(it.show)
                        }
                    }
                    is Names_Internet.ResultFail -> {
                        //outputName.post {
                        Log.e("RETROFIT_REQUEST", it.message)
                    }
                }
            }
        }
    }
}