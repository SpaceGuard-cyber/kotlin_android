/*
package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Find_Showman
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.ShowMan.ShowmanItem

class AdapterShowMan(val name:List<Find_Showman>, val mainActivity: MainActivity):RecyclerView.Adapter<AdapterShowMan.ShowmanHolder>() {
    class ShowmanHolder (val view: ViewGroup) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowmanHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_man_item, parent,false) as ViewGroup
        return ShowmanHolder(view)
    }

    override fun onBindViewHolder(holder: ShowmanHolder, position: Int) {
        val showman = name[position]
        holder.view.findViewById<TextView>(R.id.name).text = showman.name
        holder.view.findViewById<TextView>(R.id.language).text = showman.language
        holder.view.findViewById<TextView>(R.id.genre).text = showman.genre
*/
/*        holder.view.setOnClickListener {
            val intent = Intent(mainActivity, showman::class.java)
            intent.putExtra("Showman", name[position])
            mainActivity.startActivity(intent)

        }*//*

    }

    override fun getItemCount() = name.size


}

*/
