package com.example.mini7thumcapplication.Detail

import ReviewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mini7thumcapplication.R
import com.example.mini7thumcapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var profileAdapter: ProfileAdapter

    private lateinit var reviewadapter : ReviewAdapter
    val rDatas=mutableListOf<ReviewData>()

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
            ProfileData("장재현", "감독", R.drawable.profile1),
            ProfileData("최민식", "김상덕 역", R.drawable.profile2),
            ProfileData("김고은", "이화림 역", R.drawable.profile3),
            ProfileData("유해진", "고영근 역", R.drawable.profile4)
        )

        // 어댑터 설정
        profileAdapter = ProfileAdapter(profileData)

        // RecyclerView에 어댑터 설정
        binding.profileRecycler.adapter = profileAdapter

        // 레이아웃 매니저 설정
        binding.profileRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        initializelist()
        initRecylerview()

        // 스포일러 토글
        binding.categoryToggleIv.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // 토글이 켜졌을 때: 노란색 스타일 적용
                binding.categoryToggleIv.thumbTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
                binding.categoryToggleIv.trackTintList = ContextCompat.getColorStateList(requireContext(), R.color.Sub_Yellow)
            } else {
                // 토글이 꺼졌을 때: 기본 스타일로 복원
                binding.categoryToggleIv.thumbTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
                binding.categoryToggleIv.trackTintList = ContextCompat.getColorStateList(requireContext(), R.color.T_gray)
            }
        }
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

    private fun initRecylerview() {
        val adapter = ReviewAdapter()
        adapter.datalist=rDatas
        binding.reviewRecyler.adapter = adapter
        binding.reviewRecyler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initializelist() {
        with(rDatas) {

            add(ReviewData("12",true,true,true,true,true, "10", "너무 재미있어요", "2024.12.12"))
            add(ReviewData("10",true,true,true,true,true, "10", "전개가 빨라서 좋아요", "2024.12.13"))
            add(ReviewData("11",true,true,true,true,true, "10", "영화관에서 보는 거 추천드려요!", "2024.12.13"))
            add(ReviewData("13", true, true, true, true, true, "9", "재미있긴 했지만 약간 지루한 부분도 있었어요.", "2024.12.14"))
            add(ReviewData("14", true, true, true, true, false, "8", "스토리가 탄탄해서 좋았어요.", "2024.12.15"))
            add(ReviewData("15", true, true, true, false, false, "6", "생각보다 평범했어요.", "2024.12.16"))
            add(ReviewData("16", true, true, true, true, true, "10", "다시 보고 싶을 만큼 최고입니다!", "2024.12.17"))
            add(ReviewData("17", true, true, false, false, false, "4", "조금 실망스러웠습니다.", "2024.12.18"))
            add(ReviewData("18", true, true, true, true, false, "8", "전개가 흥미진진해서 좋았어요.", "2024.12.19"))
            add(ReviewData("19", true, true, true, true, true, "10", "배우들의 연기가 정말 훌륭했습니다.", "2024.12.20"))
            add(ReviewData("20", true, true, true, false, false, "7", "약간 아쉬운 결말이었지만 괜찮았어요.", "2024.12.21"))
            add(ReviewData("21", true, true, false, false, false, "5", "조금 더 재미있는 내용이었으면 좋았을 것 같아요.", "2024.12.22"))
            add(ReviewData("22", true, true, true, true, false, "8", "기대 이상으로 좋았습니다.", "2024.12.23"))
            add(ReviewData("23", true, true, true, false, false, "7", "중반부까지는 정말 재밌었어요.", "2024.12.24"))
            add(ReviewData("24", true, true, false, false, false, "5", "기대했던 것보다 평범했습니다.", "2024.12.25"))
            add(ReviewData("25", true, true, true, true, true, "10", "명작이에요! 꼭 보세요!", "2024.12.26"))
            add(ReviewData("26", true, true, true, true, false, "9", "작품성이 뛰어나요. 추천합니다.", "2024.12.27"))

        }
    }

}