package com.example.leetcoderating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var rvMain : RecyclerView
    lateinit var myAdaptor: MyAdapter
    var BASE_URL = "https://lccn.lbao.site/api/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvMain = findViewById(R.id.recycler_view)
        rvMain.layoutManager = LinearLayoutManager(this)

        getAllData()
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

        retroData.enqueue(object : Callback<List<ContestsItem>>{
            override fun onResponse(
                call: Call<List<ContestsItem>>,
                response: Response<List<ContestsItem>>
            ) {
                var data = response.body()!!
                myAdaptor = MyAdapter(baseContext,data)
                rvMain.adapter = myAdaptor
                Log.d("data",data.toString())
            }

            override fun onFailure(call: Call<List<ContestsItem>>, t: Throwable) {
                Log.e("fail", "API request failed: ${t.message}")
            }

        })
    }
}