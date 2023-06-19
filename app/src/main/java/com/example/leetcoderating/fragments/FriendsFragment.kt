package com.example.leetcoderating.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leetcoderating.FriendAdapter1
import com.example.leetcoderating.R
import java.util.LinkedList

class FriendsFragment : Fragment() {

    private lateinit var searchbar: EditText
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var recyclerView: RecyclerView
    lateinit var emptytext:TextView
    lateinit var friendAdapter1: FriendAdapter1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friends, container, false)
        searchbar = view.findViewById(R.id.search1)
        recyclerView = view.findViewById(R.id.recycler_view_2)
        sharedPreferences =requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        emptytext = view.findViewById(R.id.empty_text)
        val isFirstLaunch = sharedPreferences.getBoolean("FirstLaunch", true)
        if (isFirstLaunch) {
            // Clear the list if it's the first time app launch
            sharedPreferences.edit().remove("SavedNames").apply()
            sharedPreferences.edit().putBoolean("FirstLaunch", false).apply()
        }
        searchbar.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                performSearch()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        friendAdapter1 = FriendAdapter1(getSavedNames().toMutableList(),sharedPreferences)
        recyclerView.adapter = friendAdapter1
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        emptytext.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        return view
    }
    private fun getSavedNames(): Array<String> {
        val savedNamesSet = sharedPreferences.getStringSet("SavedNames", setOf()) ?: setOf()
        return savedNamesSet.toTypedArray()
    }
    private fun performSearch() {
        val searchText = searchbar.text.toString().trim()
        if (searchText.isNotEmpty()) {
            val savedNames = sharedPreferences.getStringSet("SavedNames", mutableSetOf())
            saveName(searchText)
            friendAdapter1.addItem(searchText)
            showToast("Name saved: $searchText")
            searchbar.text.clear()
        } else {
            showToast("Please enter a name")
        }
    }

    private fun saveName(name: String) {
        val savedNames = getSavedNames().toMutableSet()
        savedNames.add(name)
        sharedPreferences.edit().putStringSet("SavedNames", savedNames).apply()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
