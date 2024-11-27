package com.example.mini7thumcapplication.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mini7thumcapplication.R
import com.example.mini7thumcapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var profileAdapter: ProfileAdapter

    private var likeCount = 0
    private var dislikeCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val movieTitle = arguments?.getString("movieTitle") ?: "영화 제목 없음"
        binding.movieTitle.text = movieTitle

        setupRecyclerView()
        setupLikeDislikeButtons()

        return binding.root
    }

    private fun setupRecyclerView() {
        val profileData = listOf(
            ProfileData("장재현", "감독", R.drawable.item_film_temporary),
            ProfileData("최민식", "김상덕 역", R.drawable.item_film_temporary),
            ProfileData("김고은", "이화림 역", R.drawable.item_film_temporary),
            ProfileData("유해진", "고영근 역", R.drawable.item_film_temporary)
        )

        profileAdapter = ProfileAdapter(profileData)
        binding.profileRecycler.adapter = profileAdapter
        binding.profileRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    // <좋아요 / 싫어요> 관련 함수
    private fun setupLikeDislikeButtons() {
        updateLikeDislikeUI()

        binding.btnLike.setOnClickListener {
            likeCount++
            updateLikeDislikeUI()
        }

        binding.btnDislike.setOnClickListener {
            dislikeCount++
            updateLikeDislikeUI()
        }
    }

    private fun updateLikeDislikeUI() {
        binding.likeCount.text = likeCount.toString()
        binding.dislikeCount.text = dislikeCount.toString()

        val total = likeCount + dislikeCount
        if (total > 0) {
            val likeRatio = (likeCount * 100) / total
            binding.likeDislikeProgress.progress = likeRatio
        }
    }
}
