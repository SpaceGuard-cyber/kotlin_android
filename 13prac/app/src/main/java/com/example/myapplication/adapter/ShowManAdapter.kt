/*
package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ShowMan.ShowmanItem

class ShowManAdapter(val list: List<ShowmanItem>) : RecyclerView.Adapter<ShowManAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name)
        val country = itemView.findViewById<TextView>(R.id.country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.show_man_item, parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list[position].person.name
        holder.country.text = list[position].person.country.name
    }

    override fun getItemCount(): Int = list.size
}*/
