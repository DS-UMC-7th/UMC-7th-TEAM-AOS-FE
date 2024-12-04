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
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.mini7thumcapplication.R
import com.example.mini7thumcapplication.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {
    private var score: Double = 0.0

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
                binding.tvScore.text = "${(index + 1)*2}"
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


        //관람평 글자수
        binding.etContent.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                // 현재 입력된 글자 수 가져오기
                val currentLength = s?.length ?: 0

                if (currentLength > MAX_LENGTH) {
                    // 초과된 글자 삭제
                    s?.delete(MAX_LENGTH, currentLength)
                }

                // 글자수 TextView에 업데이트
                binding.etTextnum.text = "(${s?.length ?: 0}/100)"
            }
        })

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수 방지
    }

    // 별점 채우기
    private fun updateScore(selectedStar: Int) {
        val scoreImages = listOf(
            binding.ivScore1,
            binding.ivScore2,
            binding.ivScore3,
            binding.ivScore4,
            binding.ivScore5
        )

        score = selectedStar.toDouble()
        for (i in scoreImages.indices) {
            val starImage = scoreImages[i]
            if (i < selectedStar) {
                starImage.setImageResource(R.drawable.iv_fill_star) // Filled star image
            } else {
                starImage.setImageResource(R.drawable.iv_unfill_star) // Empty star image
            }
        }
    }




    // 키워드 토글 선택 함수
    private fun toggleCardViewState(cardView: CardView, textView: TextView, isClicked: Boolean) :Boolean {
        if (isClicked) {
            // 클릭 해제 상태
            cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context, android.R.color.transparent))
            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.Sub_Yellow))
            return false
        } else {
            // 클릭 상태
            cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context, R.color.Sub_Yellow))
            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.BackGround_BK))
            return true
        }
    }


    private fun SwitchCompat.setStyle(styleResId:Int) {
        context?.let {
            val style = ContextCompat.getDrawable(it, styleResId)
            this.background = style
        }
    }




//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ReviewFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}