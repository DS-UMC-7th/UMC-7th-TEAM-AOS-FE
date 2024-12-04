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
    }

    // onCreateViewHolder: 뷰를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review_recyclerview, parent, false)
        return ReviewViewHolder(view)
    }

    // onBindViewHolder: 데이터 바인딩
    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.filmName.text = review.name
        holder.filmStar.text = review.starRating
        holder.filmScore.text = review.score.toString()
        holder.filmDate.text = review.date
        holder.filmNote.text = review.note
        holder.itemFilm.setImageResource(review.imageResId) // 리소스 ID로 이미지를 설정
    }

    // getItemCount: 리스트의 아이템 개수 반환
    override fun getItemCount(): Int = reviews.size
}
