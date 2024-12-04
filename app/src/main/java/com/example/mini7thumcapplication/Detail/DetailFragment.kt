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
import com.example.mini7thumcapplication.data.repository.review.ReviewRepository
import com.example.mini7thumcapplication.databinding.FragmentDetailBinding
import com.example.mini7thumcapplication.databinding.ItemReviewRecyclerviewBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var profileAdapter: ProfileAdapter


    private lateinit var reviewAdapter: ReviewAdapter
    private val rDatas = mutableListOf<ReviewData>()

    private lateinit var reviewadapter:ReviewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        setupRecyclerView()  // Profile RecyclerView 연결
        setupReviewRecyclerView()  // Review RecyclerView 연결
        fetchReviews()  // API 호출해서 리뷰 데이터 가져오기
        return binding.root
    }

    // 프로필 RecyclerView 설정
    private fun setupRecyclerView() {
        // [감독 | 주연] 샘플 데이터 생성
        val profileData = listOf(
            ProfileData("장재현", "감독", R.drawable.item_film_temporary),
            ProfileData("최민식", "김상덕 역", R.drawable.item_film_temporary),
            ProfileData("김고은", "이화림 역", R.drawable.item_film_temporary),
            ProfileData("유해진", "고영근 역", R.drawable.item_film_temporary)
        )

        profileAdapter = ProfileAdapter(profileData)
        binding.profileRecycler.adapter = profileAdapter
        binding.profileRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    // 리뷰 RecyclerView 설정
    private fun setupReviewRecyclerView() {
        reviewAdapter = ReviewAdapter()
        binding.reviewRecyler.adapter = reviewAdapter
        binding.reviewRecyler.layoutManager = LinearLayoutManager(requireContext())
    }

    // 리뷰 API 호출
    private fun fetchReviews() {

        val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1Iiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTczMzMwMDQ3MH0.NiNYtzJfEDHPLzZAO6Ed3MqJ9jRXo_haBXde7tcMMWa_VZ_E84skL9aacZJzB8lXZfkTCKQJMfFL6mkIXGxE3g"
        val tokenWithBearer = "Bearer $accessToken"
        val movieId = 1 // 예시로 "movieId"를 1로 설정
        ReviewRepository.getMovieReviews(tokenWithBearer, movieId) { response ->
            response?.let {
                if (it.isSuccess) {
                    val reviews = it.result.reviews.map { review ->
                        ReviewData(
                            star_sum = review.rating.toString(),
                            review_time = review.createdAt,
                            review_contents = review.content
                        )
                    }

                    // Adapter에 데이터를 추가하고 RecyclerView 갱신
                    rDatas.clear()
                    rDatas.addAll(reviews)
                    reviewAdapter.datalist = rDatas
                    reviewAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}