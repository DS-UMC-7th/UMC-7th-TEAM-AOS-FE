package com.example.mini7thumcapplication.Detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mini7thumcapplication.databinding.ItemReviewBinding
import com.example.mini7thumcapplication.databinding.ItemReviewRecyclerviewBinding

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){
    var datalist = mutableListOf<ReviewData>()
    inner class ReviewViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reviewData:ReviewData){


            binding.starSum.text = reviewData.star_sum
            binding.reviewTime.text = reviewData.review_time
            binding.reviewContents.text = reviewData.review_contents
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding=ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount(): Int =datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

}