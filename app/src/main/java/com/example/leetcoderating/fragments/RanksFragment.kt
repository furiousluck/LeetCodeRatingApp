package com.example.leetcoderating.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leetcoderating.APIInterface
import com.example.leetcoderating.MyAdapter1
import com.example.leetcoderating.R
import com.example.leetcoderating.contestRanksItem
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RanksFragment : Fragment() {


    lateinit var rvMain1: RecyclerView
    lateinit var myAdaptor1: MyAdapter1
    lateinit var rl1: RelativeLayout
    var BASE_URL = "https://lccn.lbao.site/api/v1/"
    lateinit var progress: ProgressBar
    lateinit var searchbar: EditText
    lateinit var cardView: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_ranks, container, false)
        val contestid = requireActivity().intent.getStringExtra("contestId")
        if (contestid != null) {
            Log.d("data", contestid)
        }
        rvMain1 = view.findViewById(R.id.recycler_view_1)
        searchbar = view.findViewById(R.id.search)
        cardView = view.findViewById(R.id.cd1)
        rvMain1.layoutManager = LinearLayoutManager(requireContext())
        myAdaptor1 = MyAdapter1(requireContext(), emptyList())
        rvMain1.adapter = myAdaptor1
        progress = view.findViewById(R.id.bar1)
        rl1 = view.findViewById(R.id.rl1)
        cardView.visibility = View.GONE
        getAllData(contestid)
        searchbar.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                performSearch()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        return view
    }
    private fun performSearch() {
        val query = searchbar.text.toString().trim()
        val contestid = requireActivity().intent.getStringExtra("contestId")
        if (query.isNotEmpty()) {
            getUserData(contestid,query)
            progress.visibility = View.VISIBLE
            rvMain1.visibility = View.GONE
            searchbar.setText("")
        }
        else
        {
            getAllData(contestid)
            progress.visibility = View.VISIBLE
            rvMain1.visibility = View.GONE
            rl1.visibility = View.GONE
        }
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
                        rl1.visibility = View.VISIBLE
                        val data = response.body()!!
                        myAdaptor1 = MyAdapter1(requireContext(), data)
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

    private fun getAllData(contestid: String?) {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val apiInterface = retrofit.create(APIInterface::class.java)
        contestid?.let { apiInterface.getRanks(it) }
            ?.enqueue(object : Callback<List<contestRanksItem>> {
                override fun onResponse(
                    call: Call<List<contestRanksItem>>,
                    response: Response<List<contestRanksItem>>
                ) {
                    if (response.isSuccessful) {
                        progress.visibility = View.GONE
                        rvMain1.visibility = View.VISIBLE
                        rl1.visibility = View.VISIBLE
                        cardView.visibility= View.VISIBLE
                        val data = response.body()!!
                        myAdaptor1 = MyAdapter1(requireContext(), data)
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