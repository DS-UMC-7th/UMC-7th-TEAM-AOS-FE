package com.example.mini7thumcapplication.Review

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.mini7thumcapplication.R
import com.example.mini7thumcapplication.data.repository.review.ReviewRepository
import com.example.mini7thumcapplication.data.repository.review.ReviewRequest
import com.example.mini7thumcapplication.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {
    private var score: Int? = 10
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    private val MAX_LENGTH = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 바인딩 초기화
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 스코어 조절
        val scoreImages = listOf(
            binding.ivScore1,
            binding.ivScore2,
            binding.ivScore3,
            binding.ivScore4,
            binding.ivScore5
        )

        scoreImages.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                updateScore(index + 1)
                binding.tvScore.text = "${(index + 1) * 2}"
                Log.d("score테스트", score.toString())
            }
        }

        // 감상 포인트 키워드
        val keywordCard = listOf(
            binding.cvKeywordProduce,
            binding.cvKeywordAct,
            binding.cvKeywordStory,
            binding.cvKeywordVideoView,
            binding.cvKeywordOst
        )

        val keywordTv = listOf(
            binding.tvKeywordProduce,
            binding.tvAct,
            binding.tvStory,
            binding.tvVideoView,
            binding.tvOst
        )

        // 클릭 상태를 저장하는 리스트
        val clickedStates = MutableList(keywordCard.size) { false }

        keywordCard.forEachIndexed { index, cardView ->
            cardView.setOnClickListener {
                // 상태를 토글하고 업데이트
                clickedStates[index] = toggleCardViewState(cardView, keywordTv[index], clickedStates[index])
            }
        }

        // 관람평 글자수
        binding.etContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val currentLength = s?.length ?: 0
                if (currentLength > MAX_LENGTH) {
                    s?.delete(MAX_LENGTH, currentLength)
                }
                binding.etTextnum.text = "(${s?.length ?: 0}/100)"
            }
        })

        // 스포일러 토글
        binding.categoryToggleIv.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.categoryToggleIv.thumbTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
                binding.categoryToggleIv.trackTintList = ContextCompat.getColorStateList(requireContext(), R.color.Sub_Yellow)
            } else {
                binding.categoryToggleIv.thumbTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
                binding.categoryToggleIv.trackTintList = ContextCompat.getColorStateList(requireContext(), R.color.T_gray)
            }
        }

        // 업로드 버튼 클릭 리스너
        binding.btnUpload.setOnClickListener {
            uploadReview()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수 방지
    }

    private fun updateScore(selectedStar: Int) {
        val scoreImages = listOf(
            binding.ivScore1,
            binding.ivScore2,
            binding.ivScore3,
            binding.ivScore4,
            binding.ivScore5
        )

        score = selectedStar
        for (i in scoreImages.indices) {
            val starImage = scoreImages[i]
            if (i < selectedStar) {
                starImage.setImageResource(R.drawable.iv_fill_star) // Filled star image
            } else {
                starImage.setImageResource(R.drawable.iv_unfill_star) // Empty star image
            }
        }
    }

    private fun toggleCardViewState(cardView: CardView, textView: TextView, isClicked: Boolean): Boolean {
        return if (isClicked) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context, android.R.color.transparent))
            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.Sub_Yellow))
            false
        } else {
            cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context, R.color.Sub_Yellow))
            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.BackGround_BK))
            true
        }
    }

    private fun uploadReview() {
        var accessToken = " eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1Iiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTczMzI5OTQ5N30.XQnyxCyJfvE6WS4Mq52TZ3crl_9OVvWqv47FFQfGYNWjV6F1cWZ5GHZGknSngWIaaphWXeowDefg3pjwiqZwtA"

        val rating = score ?: run {
            Toast.makeText(context, "별점을 선택해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedTags = mutableListOf<Int>()
        val keywords = listOf(
            binding.cvKeywordProduce to 1,
            binding.cvKeywordAct to 2,
            binding.cvKeywordStory to 3,
            binding.cvKeywordVideoView to 4,
            binding.cvKeywordOst to 5
        )

        keywords.forEach { (cardView, tag) ->
            if (cardView.cardBackgroundColor.defaultColor == ContextCompat.getColor(requireContext(), R.color.Sub_Yellow)) {
                selectedTags.add(tag)
            }
        }

        if (selectedTags.isEmpty()) {
            Toast.makeText(context, "최소 하나의 키워드를 선택해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val content = binding.etContent.text.toString()
        if (content.isEmpty()) {
            Toast.makeText(context, "리뷰 내용을 작성해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val isSpoiled = binding.categoryToggleIv.isChecked

        val reviewRequest = ReviewRequest(
            movieId = 1,
            rating = 8,
            tags = selectedTags,
            content = content,
            spoiled = isSpoiled
        )

        ReviewRepository.uploadReview(accessToken, reviewRequest) { response ->
            if (response != null) {
                Toast.makeText(context, "리뷰 업로드 성공: ${response.message}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "리뷰 업로드 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
