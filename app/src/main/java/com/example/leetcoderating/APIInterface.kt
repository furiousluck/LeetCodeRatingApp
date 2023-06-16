package com.example.leetcoderating

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("contests")
    fun getData(): Call<List<ContestsItem>>

    @GET("contest-records/")
    fun getRanks(@Query("contest_name") contest_name:String): Call<List<contestRanksItem>>

    @GET("contest-records/user")
    fun getUser(@Query("contest_name") contest_name: String,@Query("username") username:String): Call<List<contestRanksItem>>
}