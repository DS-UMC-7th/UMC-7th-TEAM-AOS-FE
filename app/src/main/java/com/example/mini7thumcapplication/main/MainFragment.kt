package com.example.mini7thumcapplication.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mini7thumcapplication.Detail.DetailFragment
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
        val recommendedMovies = getRecommendedMovies()
        val recentMovies = getRecentMovies()
        val reviewMovies = getReviewMovies()

        recommendedMoviesAdapter = MovieAdapter(recommendedMovies) { movie ->
            navigateToDetail(movie)
        }
        recentMoviesAdapter = MovieAdapter(recentMovies) { movie ->
            navigateToDetail(movie)
        }
        reviewMoviesAdapter = MovieAdapter(reviewMovies) { movie ->
            navigateToDetail(movie)
        }

        binding.recommendedMoviesRecycler.adapter = recommendedMoviesAdapter
        binding.recommendedMoviesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recentMoviesRecycler.adapter = recentMoviesAdapter
        binding.recentMoviesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.reviewMoviesRecycler.adapter = reviewMoviesAdapter
        binding.reviewMoviesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun navigateToDetail(movie: MovieData) {
        val detailFragment = DetailFragment().apply {
            arguments = Bundle().apply {
                putString("movieTitle", movie.title)
                putInt("movieImage", movie.m_image)
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
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
