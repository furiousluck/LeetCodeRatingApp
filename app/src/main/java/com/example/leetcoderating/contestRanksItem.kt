package com.example.leetcoderating

data class contestRanksItem(
    val _id: String,
    val attendedContestsCount: Int,
    val contest_id: Int,
    val contest_name: String,
    val country_code: String,
    val country_name: String,
    val data_region: String,
    val delta_rating: Double,
    val finish_time: String,
    val insert_time: String,
    val new_rating: Double,
    val old_rating: Double,
    val predict_time: String,
    val rank: Int,
    val score: Int,
    val user_slug: String,
    val username: String
)