package com.example.mini7thumcapplication.MyPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mini7thumcapplication.R
import com.example.mini7thumcapplication.databinding.ItemReviewBinding

//영화 리스트 데이터//
data class Review(
    val name: String,
    val starRating: String,
    val score: Int,
    val date: String,
    val note: String,
    val imageResId: Int // 이미지 리소스를 참조
)


// 어댑터
class ReviewAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    // ViewHolder 클래스
    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemFilm: ImageView = itemView.findViewById(R.id.item_film)
        val filmName: TextView = itemView.findViewById(R.id.film_name)
        val filmStar: TextView = itemView.findViewById(R.id.film_star)
        val filmScore: TextView = itemView.findViewById(R.id.film_score)
        val filmDate: TextView = itemView.findViewById(R.id.film_date)
        val filmNote: TextView = itemView.findViewById(R.id.film_note)
        val itemLayout: View = itemView // 전체 레이아웃
    }

    // onCreateViewHolder: 뷰 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review_recyclerview, parent, false)
        return ReviewViewHolder(view)
    }

    // onBindViewHolder: 데이터 바인딩
    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]

        // 데이터 설정
        holder.filmName.text = review.name
        holder.filmStar.text = review.starRating
        holder.filmScore.text = review.score.toString()
        holder.filmDate.text = review.date
        holder.filmNote.text = review.note
        holder.itemFilm.setImageResource(review.imageResId)

        // 배경색을 기존 서식 위에 Tint로 변경
        if (position % 2 == 0) {
            holder.itemLayout.backgroundTintList = holder.itemView.context.getColorStateList(R.color.Sub_Yellow) // 짝수
        } else {
            holder.itemLayout.backgroundTintList = holder.itemView.context.getColorStateList(R.color.sub3) // 홀수
        }
    }

    // getItemCount: 아이템 개수 반환
    override fun getItemCount(): Int = reviews.size
}
