package com.example.mini7thumcapplication.MyPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mini7thumcapplication.R
import com.example.mini7thumcapplication.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReviewAdapter
    private lateinit var reviews: List<Review>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)

        // RecyclerView 초기화
        recyclerView = binding.recyclerReviews // Fragment에서 ViewBinding 사용하여 recyclerView 접근
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 리뷰 데이터 생성
        reviews = listOf(
            Review(
                name = "파묘",
                starRating = "★ ★ ★ ★ ★",
                score = 10,
                date = "2024.11.21 13:43",
                note = "배우들 연기가 진짜 미쳤음!! 특히 김고은 연기... 와 선배 배우들이 왜 칭찬했는지 알거 같음",
                imageResId = R.drawable.item_film_temporary // 리소스를 직접 참조
            ),
            // 더 많은 리뷰 항목을 추가
        )

        // 어댑터 설정
        adapter = ReviewAdapter(reviews)
        recyclerView.adapter = adapter

        // 최신순/별점순 메뉴 처리
        binding.tvSortOption.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.tvSortOption)    // PopupMenu를 tv_sort_option에 연결
            val inflater = popupMenu.menuInflater  // context -> 레이아웃 변환
            inflater.inflate(R.menu.sort_menu, popupMenu.menu)  // sort_menu.xml 사용

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.sort_by_latest -> {
                        binding.tvSortOption.text = "최신순"
                        true
                    }
                    R.id.sort_by_rating -> {
                        binding.tvSortOption.text = "별점순"
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
