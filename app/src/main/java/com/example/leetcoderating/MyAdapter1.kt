package com.example.leetcoderating

import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter1(var con:Context, var list:List<contestRanksItem>):RecyclerView.Adapter<MyAdapter1.ViewHolder>() {

    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var rank = v.findViewById<TextView>(R.id.ranks)
        var username  = v.findViewById<TextView>(R.id.names)
        var delta = v.findViewById<TextView>(R.id.delta)
        var score = v.findViewById<TextView>(R.id.score)
        var new_rating = v.findViewById<TextView>(R.id.new_rating)
        var old_rating = v.findViewById<TextView>(R.id.old_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(con).inflate(R.layout.rank_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.rank.text = currentItem.rank.toString()
        holder.username.text = currentItem.username
        val decimalFormat = DecimalFormat("#")
        var x1 = decimalFormat.format(currentItem.delta_rating).toString()
        if(currentItem.delta_rating>0.0)
        {
            x1= "+$x1"
        }
        holder.delta.text = x1
        val x = "Score: "+ currentItem.score.toString()
        holder.score.text = x
        val y = "Old Rating: "+ decimalFormat.format(currentItem.old_rating).toString()
        val z = "New Rating: "+ decimalFormat.format(currentItem.new_rating).toString()
        holder.old_rating.text = y;
        holder.new_rating.text = z;

    }


}