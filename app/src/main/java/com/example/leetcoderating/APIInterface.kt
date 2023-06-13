package com.example.leetcoderating

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("contests")
    fun getData(): Call<List<ContestsItem>>
}