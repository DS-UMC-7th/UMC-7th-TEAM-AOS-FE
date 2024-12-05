package com.example.mini7thumcapplication.data.repository.review

import com.google.gson.annotations.SerializedName


// 리뷰 업로드 응답 데이터 클래스
data class ReviewUploadResponse (
    @SerializedName("reviewId") val reviewId: String,
    @SerializedName("message") val message: String,


)



// 리뷰 세부사항 응답 데이터 클래스
data class ReviewDetailResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result
) {
    // 리뷰 결과 클래스
    data class Result(
        @SerializedName("movieId") val movieId: Int,
        @SerializedName("reviews") val reviews: List<Review>
    )

    // 리뷰 클래스
    data class Review(
        @SerializedName("reviewId") val reviewId: Int,
        @SerializedName("rating") val rating: Int,
        @SerializedName("content") val content: String,
        @SerializedName("isSpoiled") val isSpoiled: Boolean,
        @SerializedName("createdAt") val createdAt: String,
        @SerializedName("updatedAt") val updatedAt: String,
        @SerializedName("tagReviewList") val tagReviewList: List<TagReview>
    )

    // 태그 클래스
    data class TagReview(
        @SerializedName("tagId") val tagId: Int,
        @SerializedName("tagName") val tagName: String
    )
}