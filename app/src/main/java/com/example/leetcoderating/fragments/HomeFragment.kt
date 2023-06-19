package com.example.leetcoderating.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leetcoderating.APIInterface
import com.example.leetcoderating.ContestsItem
import com.example.leetcoderating.MainActivity2
import com.example.leetcoderating.MyAdapter
import com.example.leetcoderating.R
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    lateinit var rvMain : RecyclerView
    lateinit var myAdaptor: MyAdapter
    var BASE_URL = "https://lccn.lbao.site/api/v1/"
    lateinit var loading : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        rvMain = view.findViewById(R.id.recycler_view)
        rvMain.layoutManager = LinearLayoutManager(requireContext())
        loading = view.findViewById(R.id.bar)
        getAllData()

        return view
    }

    private fun getAllData() {
        var gson = GsonBuilder()
            .setLenient()
            .create()
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        var apiInterface = retrofit.create(APIInterface::class.java)

        var retroData = apiInterface.getData()

        retroData.enqueue(object : Callback<List<ContestsItem>> {
            override fun onResponse(
                call: Call<List<ContestsItem>>,
                response: Response<List<ContestsItem>>
            ) {
                loading.visibility = View.GONE
                rvMain.visibility = View.VISIBLE
                var data = response.body()!!
                myAdaptor = MyAdapter(requireContext(),data)
                rvMain.adapter = myAdaptor

                myAdaptor.setOnItemClickListener(object : MyAdapter.OnItemClickListener{
                    override fun onItemClick(contestItem: ContestsItem) {
                        val intent = Intent(requireActivity(), MainActivity2::class.java)
                        // Pass any necessary data to the second activity
                        intent.putExtra("contestId", contestItem.titleSlug)
                        startActivity(intent)
                    }

                })
                Log.d("data",data.toString())
            }

            override fun onFailure(call: Call<List<ContestsItem>>, t: Throwable) {
                Log.e("fail", "API request failed: ${t.message}")
            }

        })
    }
}