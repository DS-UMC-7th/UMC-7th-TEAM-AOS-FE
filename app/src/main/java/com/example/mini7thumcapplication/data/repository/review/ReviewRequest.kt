package com.example.mini7thumcapplication.data.repository.review


import com.google.gson.annotations.SerializedName
data class ReviewRequest (
    @SerializedName("movieId") val movieId: Int,
    @SerializedName("rating") val rating: Int,
    @SerializedName("tags") val tags: List<Int>,
    @SerializedName("content") val content: String,
    @SerializedName("spoiled") val spoiled: Boolean
)