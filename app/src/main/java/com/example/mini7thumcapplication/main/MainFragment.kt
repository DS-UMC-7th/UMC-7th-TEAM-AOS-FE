package com.example.mini7thumcapplication.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mini7thumcapplication.databinding.FragmentMainBinding
import com.example.mini7thumcapplication.R
import com.example.mini7thumcapplication.databinding.FragmentDetailBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var recommendedMoviesAdapter: MovieAdapter
    private lateinit var recentMoviesAdapter: MovieAdapter
    private lateinit var reviewMoviesAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setupRecyclerViews()
        return binding.root
    }

    private fun setupRecyclerViews() {
        // 샘플 데이터 생성
        val recommendedMovies = getRecommendedMovies()
        val recentMovies = getRecentMovies()
        val reviewMovies = getReviewMovies()

        // 어댑터 설정
        recommendedMoviesAdapter = MovieAdapter(recommendedMovies)
        recentMoviesAdapter = MovieAdapter(recentMovies)
        reviewMoviesAdapter = MovieAdapter(reviewMovies)

        // RecyclerView에 어댑터 설정
        binding.recommendedMoviesRecycler.adapter = recommendedMoviesAdapter
        binding.recentMoviesRecycler.adapter = recentMoviesAdapter
        binding.reviewMoviesRecycler.adapter = reviewMoviesAdapter

        // 레이아웃 매니저 설정
        binding.recommendedMoviesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recentMoviesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reviewMoviesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    // [오늘의 추천 영화] 샘플 데이터
    private fun getRecommendedMovies(): List<MovieData> {
        return listOf(
            MovieData("조커", R.drawable.item_film_temporary),
            MovieData("베테랑2", R.drawable.item_film_temporary),
            MovieData("대도시의 사랑법", R.drawable.item_film_temporary),
        )
    }

    // [최근 개봉한 영화] 샘플 데이터
    private fun getRecentMovies(): List<MovieData> {
        return listOf(
            MovieData("베테랑2", R.drawable.item_film_temporary),
            MovieData("조커", R.drawable.item_film_temporary),
            MovieData("파묘", R.drawable.item_film_temporary),
        )
    }

    // [리뷰가 좋은 영화] 샘플 데이터
    private fun getReviewMovies(): List<MovieData> {
        return listOf(
            MovieData("대도시의 사랑법", R.drawable.item_film_temporary),
            MovieData("파묘", R.drawable.item_film_temporary),
            MovieData("조커", R.drawable.item_film_temporary),
        )
    }

}
