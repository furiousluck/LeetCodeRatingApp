package com.example.leetcoderating

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class friendAdapter1(private var friendList: MutableList<String>) :
    RecyclerView.Adapter<friendAdapter1.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val friendNameTextView: TextView = itemView.findViewById(R.id.friendname)
        private val deleteButton: Button = itemView.findViewById(R.id.frienddelete)

        fun bind(friendName: String) {
            friendNameTextView.text = friendName
        }

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    removeFriend(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }
    fun getFriendList(): MutableList<String> {
        return friendList
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friendName = friendList[position]
        holder.bind(friendName)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    private fun removeFriend(position: Int) {
        friendList.removeAt(position)
        notifyDataSetChanged()
    }


    fun updateFriendList(newFriendList: MutableList<String>) {
        friendList = newFriendList
        notifyDataSetChanged()
    }
}
