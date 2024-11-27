package com.example.mini7thumcapplication.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mini7thumcapplication.R
import com.example.mini7thumcapplication.databinding.FragmentDetailBinding
import com.example.mini7thumcapplication.databinding.ItemReviewRecyclerviewBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var profileAdapter: ProfileAdapter

    private lateinit var reviewadapter:ReviewAdapter
    val rDatas=mutableListOf<ReviewData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        // [감독 | 주연] 샘플 데이터 생성
        val profileData = listOf(
            ProfileData("장재현", "감독", R.drawable.item_film_temporary),
            ProfileData("최민식", "김상덕 역", R.drawable.item_film_temporary),
            ProfileData("김고은", "이화림 역", R.drawable.item_film_temporary),
            ProfileData("유해진", "고영근 역", R.drawable.item_film_temporary)
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

    private fun initRecylerview() {
        val adapter = ReviewAdapter()
        adapter.datalist=rDatas
        binding.reviewRecyler.adapter = adapter
        binding.reviewRecyler.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun initializelist() {
        with(rDatas) {

            add(ReviewData("12",true,true,true,true,true, "10", "너무 재미있어요", "2024.12.12"))
        }
    }




}
