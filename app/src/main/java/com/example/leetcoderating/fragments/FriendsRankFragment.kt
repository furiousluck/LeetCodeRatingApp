package com.example.leetcoderating.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leetcoderating.APIInterface
import com.example.leetcoderating.FriendAdapter1
import com.example.leetcoderating.MyAdapter1
import com.example.leetcoderating.MyAdapter2
import com.example.leetcoderating.R
import com.example.leetcoderating.contestRanksItem
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FriendsRankFragment : Fragment() {

    lateinit var rvMain1: RecyclerView
    lateinit var myAdaptor1: MyAdapter2
    var BASE_URL = "https://lccn.lbao.site/api/v1/"
    lateinit var progress: ProgressBar
    lateinit var friendAdapter1: FriendAdapter1
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_friends_rank, container, false)
        val contestid = requireActivity().intent.getStringExtra("contestId")
        if (contestid != null) {
            Log.d("data", contestid)
        }
        rvMain1 = view.findViewById(R.id.recycler_view_3)
        rvMain1.layoutManager = LinearLayoutManager(requireContext())
        myAdaptor1 = MyAdapter2(requireContext(), emptyList())
        rvMain1.adapter = myAdaptor1
        progress = view.findViewById(R.id.bar2)
        sharedPreferences =requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        friendAdapter1 = FriendAdapter1(getSavedNames().toMutableList(), sharedPreferences)
        rvMain1.adapter = friendAdapter1
        rvMain1.layoutManager = LinearLayoutManager(requireContext())
        Log.d("listview", friendAdapter1.itemCount.toString())
        friendAdapter1.items.forEachIndexed { index, item ->
            getUserData(contestid,item)
        }
        return view
    }

    private fun getSavedNames(): Array<String> {
        val savedNamesSet = sharedPreferences.getStringSet("SavedNames", setOf()) ?: setOf()
        return savedNamesSet.toTypedArray()
    }
    private fun getUserData(contestid: String?, query: String) {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val apiInterface = retrofit.create(APIInterface::class.java)
        contestid?.let { apiInterface.getUser(it,query) }
            ?.enqueue(object : Callback<List<contestRanksItem>> {
                override fun onResponse(
                    call: Call<List<contestRanksItem>>,
                    response: Response<List<contestRanksItem>>
                ) {
                    if (response.isSuccessful) {
                        progress.visibility = View.GONE
                        rvMain1.visibility = View.VISIBLE
                        val data = response.body()!!
                        myAdaptor1 = MyAdapter2(requireContext(), data)
                        rvMain1.adapter = myAdaptor1
                        Log.d("data", data.toString())
                    } else {

                        Log.e("fail", "API request failed with response code: ${response.code()}")
                        Log.e("fail", "API request failed with response code: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<contestRanksItem>>, t: Throwable) {
                    Log.e("fail", "API request failed: ${t.message}")
                }

            })

    }
}