package com.example.leetcoderating

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var con: Context, var list: List<ContestsItem>): RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    inner class ViewHolder(v:View):RecyclerView.ViewHolder(v)
    {
        var name = v.findViewById<TextView>(R.id.contestname)
        var date = v.findViewById<TextView>(R.id.contestdate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val view = LayoutInflater.from(con).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.name.text = currentItem.title
        val dateTime = currentItem.startTime
        val date = dateTime.substring(0, 10)
        holder.date.text = date
    }

    override fun getItemCount(): Int {
        return list.count()
    }


}