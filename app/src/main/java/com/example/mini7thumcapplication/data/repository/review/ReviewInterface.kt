package com.example.mini7thumcapplication.data.repository.review

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewInterface {

    @Headers("Content-Type: application/json")
    @POST("/api/review")
    fun reviewUpload(
        @Header("Authorization") accessToken:String,
        @Body request: ReviewRequest?
    ) :Call<ReviewUploadResponse>



    @Headers("Content-Type: application/json")
    @GET("/api/review/{movieId}")
    fun getReviews(
        @Header("Authorization") accessToken:String,
        @Path("movieId") movieId: Int
    ): Call<ReviewDetailResponse>
}


