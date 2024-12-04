package com.example.mini7thumcapplication.data.repository.review

import android.util.Log
import com.example.mini7thumcapplication.data.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewRepository {

    companion object{

        fun uploadReview(accessToken: String, reviewRequest: ReviewRequest?, callback: (ReviewUploadResponse?) -> Unit) {

            val reviewApiService = getRetrofit().create(ReviewInterface::class.java)
            val call = reviewApiService.reviewUpload(accessToken, reviewRequest)

            call.enqueue(object : Callback<ReviewUploadResponse> {

                override fun onResponse(call: Call<ReviewUploadResponse>, response: Response<ReviewUploadResponse>) {
                   if (response.isSuccessful) {
                       Log.d("ReviewRepository", "리뷰 업로드 성공: ${response.body()?.message}")
                       callback(response.body())
                   } else {
                       Log.d("Authorization Header", "Bearer $accessToken")
                       Log.e("ReviewRepository", "리뷰 업로드 실패: HTTP ${response.code()} - ${response.errorBody()?.string()}")

                       callback(null)
                   }
                }

                override fun onFailure(call: Call<ReviewUploadResponse>, t: Throwable) {
                    Log.e("ReviewRepository", "리뷰 업로드 전송 오류: ${t.message}")
                    callback(null)
                }
            })



        }


        // 영화 리뷰 목록 조회 함수
        fun getMovieReviews(accessToken: String, movieId: Int, callback: (ReviewDetailResponse?) -> Unit) {
            val reviewApiService = getRetrofit().create(ReviewInterface::class.java)
            val call = reviewApiService.getReviews(accessToken,movieId)

            call.enqueue(object : Callback<ReviewDetailResponse> {
                override fun onResponse(call: Call<ReviewDetailResponse>, response: Response<ReviewDetailResponse>) {
                    if (response.isSuccessful) {
                        Log.d("ReviewRepository", "리뷰 목록 조회 성공")
                        callback(response.body())
                    } else {
                        Log.e("ReviewRepository", "리뷰 목록 조회 실패: HTTP ${response.code()} - ${response.errorBody()?.string()}")
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<ReviewDetailResponse>, t: Throwable) {
                    Log.e("ReviewRepository", "리뷰 목록 조회 오류: ${t.message}")
                    callback(null)
                }
            })
        }


    }



}