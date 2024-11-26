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
    }

}
