package com.example.leetcoderating

data class ContestsItem(
    val _id: String,
    val duration: Int,
    val endTime: String,
    val past: Boolean,
    val predict_time: String,
    val startTime: String,
    val title: String,
    val titleSlug: String,
    val update_time: String
)