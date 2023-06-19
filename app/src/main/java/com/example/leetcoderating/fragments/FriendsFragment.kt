package com.example.leetcoderating.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
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
import com.example.leetcoderating.R
import com.example.leetcoderating.friendAdapter1

class FriendsFragment : Fragment() {

    private lateinit var searchbar: EditText
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var recyclerView: RecyclerView
    lateinit var friendAdapter:friendAdapter1
    lateinit var emptytext:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friends, container, false)
        searchbar = view.findViewById(R.id.search1)
        recyclerView = view.findViewById(R.id.recycler_view_2)
        sharedPreferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        emptytext = view.findViewById(R.id.empty_text)
        searchbar.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                performSearch()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        friendAdapter = friendAdapter1(getSavedNames().toMutableList())
        recyclerView.adapter = friendAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        updateViewVisibility()
        return view
    }

    private fun getSavedNames(): Array<String> {
        val savedNamesSet = sharedPreferences.getStringSet("SavedNames", setOf()) ?: setOf()
        return savedNamesSet.toTypedArray()
    }
    private fun updateViewVisibility() {
        if (friendAdapter.itemCount == 0) {
            emptytext.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptytext.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }
    private fun performSearch() {
        val searchText = searchbar.text.toString().trim()
        if (searchText.isNotEmpty()) {
            if (friendAdapter.getFriendList().contains(searchText)) {
                showToast("Error: Same name entered twice")
            } else {
                saveName(searchText)
                friendAdapter.updateFriendList(getSavedNames().toMutableList())
                showToast("Name saved: $searchText")
                searchbar.text.clear()
                updateViewVisibility()
            }
        } else {
            showToast("Please enter a name")
        }
    }

    private fun saveName(name: String) {
        val savedNames = sharedPreferences.getStringSet("SavedNames", setOf())?.toMutableSet()
        savedNames?.add(name)
        sharedPreferences.edit().putStringSet("SavedNames", savedNames).apply()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
