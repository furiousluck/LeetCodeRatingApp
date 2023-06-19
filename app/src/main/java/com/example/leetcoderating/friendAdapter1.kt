package com.example.leetcoderating

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendAdapter1(var items: MutableList<String>, private val sharedPreferences: SharedPreferences) : RecyclerView.Adapter<FriendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return FriendViewHolder(view).linkAdapter(this)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun addItem(name: String) {
        items.add(name)
        notifyItemInserted(items.size - 1)
    }
    fun removeItem(position: Int) {
        if (position in 0 until items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
            saveItemsToSharedPreferences()
        }
    }

    private fun saveItemsToSharedPreferences() {
        val editor = sharedPreferences.edit()
        editor.putStringSet("SavedNames", items.toSet())
        editor.apply()
    }
}

class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.friendname)
    private var adapter: FriendAdapter1? = null

    init {
        itemView.findViewById<View>(R.id.frienddelete).setOnClickListener { view ->
            adapter?.let {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    it.removeItem(position)
                }
            }
        }
    }

    fun linkAdapter(adapter: FriendAdapter1): FriendViewHolder {
        this.adapter = adapter
        return this
    }
}
