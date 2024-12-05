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
                imageResId = R.drawable.movie1 // 리소스를 직접 참조
            ),
            Review(
                name = "와일드 로봇",
                starRating = "★ ★ ★ ★ ☆",
                score = 8,
                date = "2024.11.21. 13:43",
                note = "초2,7살,저 모두 펑펑 울며 봤어요. 마음이 따뜻해지는 정말 좋은 영화였어요. 저에겐 오래도록 떠오르는 영화가 될 것 같은데, 관람석이 너무 한산해서 안타깝더라구요. 아이들과 꼭 보셨으면 좋겠어요~!!!",
                imageResId = R.drawable.movie4 // 리소스를 직접 참조
            ),
            Review(
                name = "노트북",
                starRating = "★ ★ ★ ★ ★",
                score = 10,
                date = "2024.11.11. 12:43",
                note = "어디가서 다시는 만나지 못할 제게 특별한 사람을 이 영화를 함께 보고 만나게 되었습니다. 특별할 것 없는 이야기인 것 같은데 왜인지 가슴이 뜨거워지고 벅찬 영화였습니다.",
                imageResId = R.drawable.movie3 // 리소스를 직접 참조
            ),
            Review(
                name = "대도시의 사랑법",
                starRating = "★ ★ ★ ★ ★",
                score = 10,
                date = "2024.10.11. 11:43",
                note = "올해 본 한국영화 중 더할 나위 없이 깔끔한 영화. 웃고 울다 보니 영화가 끝나있었다. 나의 대학 시절부터 우정까지, 많은 생각이 들었다. 행복한 기분은 덤 ㅎㅎ",
                imageResId = R.drawable.movie5 // 리소스를 직접 참조
            ),
            Review(
                name = "베테랑2",
                starRating = "★ ★ ★ ★ ☆",
                score = 8,
                date = "2024.09.29. 12:43",
                note = "정해인의 돌아버린 눈알을보면 그동안 했던 연기에서 볼 수 없었던 광기를 볼 수 있다.황정민 연기야 말하기 입아플정도,다만 같은 팀 동료들의 존재감과 분량은 많이 아쉽다.",
                imageResId = R.drawable.movie2 // 리소스를 직접 참조
            ),

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
