package ar.com.wolox.android.example.ui.viewpager.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.responses.Page
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.collections.ArrayList

class NewsAdapter constructor(var context: Context, var listNews: ArrayList<Page>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    val formatDate = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.apply {
            newsContent.setText(listNews[position].comment)
            newsTitle.setText(listNews[position].commenter)
            if (listNews[position].likes.size > 0) {
                newsLike.setImageResource(R.drawable.ic_favorite)
            } else {
                newsLike.setImageResource(R.drawable.ic_favorite_border)
            }
            Glide.with(context).load(listNews[position].avatar).error(R.mipmap.ic_launcher).into(newsPhoto)
            val time = SimpleDateFormat(formatDate).parse(listNews[position].created_at).time
            val newsTimeAgo = PrettyTime().format(Date(time))
            holder.newsTime.setText(newsTimeAgo)
        }
    }

    override fun getItemCount(): Int {
        if (listNews.size > 0) {
            return listNews.size
        }
        return 0
    }

    class NewsViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var newsPhoto: ImageView
        var newsTitle: TextView
        var newsContent: TextView
        var newsTime: TextView
        var newsLike: ImageView

        init {
            newsPhoto = itemView.findViewById(R.id.imageViewPhoto)
            newsTitle = itemView.findViewById(R.id.textViewTitle)
            newsContent = itemView.findViewById(R.id.textViewContent)
            newsTime = itemView.findViewById(R.id.textViewTime)
            newsLike = itemView.findViewById(R.id.imageViewLike)
        }
    }
}