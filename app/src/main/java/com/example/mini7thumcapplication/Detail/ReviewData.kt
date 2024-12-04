package com.example.mini7thumcapplication.Detail

data class ReviewData(
    val user_profile : String,
    val star1 : Boolean,
    val star2 : Boolean,
    val star3 : Boolean,
    val star4 : Boolean,
    val star5 : Boolean,
    val star_sum : String,
    val review_contents: String,
    val review_time : String
)