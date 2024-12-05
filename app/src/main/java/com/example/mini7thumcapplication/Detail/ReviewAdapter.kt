import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mini7thumcapplication.Detail.ReviewData
import com.example.mini7thumcapplication.R
import com.example.mini7thumcapplication.databinding.ItemReviewBinding

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    var datalist = mutableListOf<ReviewData>()

    inner class ReviewViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reviewData: ReviewData, position: Int) {
            // 데이터 바인딩
            binding.starSum.text = reviewData.star_sum
            binding.reviewTime.text = reviewData.review_time
            binding.reviewContents.text = reviewData.review_contents

            // 배경색 설정
            if (position >= 1) {
                binding.root.setBackgroundResource(R.drawable.item_review_recyclerview_gray) // 회색 배경
            } else {
                binding.root.setBackgroundResource(R.drawable.item_review_recyclerview) // 기본 배경
            }

            // 별 채우기
            val starCount = reviewData.star_sum.toInt() / 2 // 점수를 별 개수로 변환 (10점 = 5개)
            val starViews = listOf(
                binding.ivUnfillItemStar1,
                binding.ivUnfillItemStar2,
                binding.ivUnfillItemStar3,
                binding.ivUnfillItemStar4,
                binding.ivUnfillItemStar5
            )

            // 별 상태 업데이트
            for (i in starViews.indices) {
                if (i < starCount) {
                    starViews[i].setImageResource(R.drawable.iv_fill_star) // 채워진 별
                } else {
                    starViews[i].setImageResource(R.drawable.iv_unfill_star) // 빈 별
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(datalist[position], position)
    }
}
