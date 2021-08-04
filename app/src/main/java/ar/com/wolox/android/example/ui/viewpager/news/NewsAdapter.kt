package ar.com.wolox.android.example.ui.viewpager.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.ItemNewsModel
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.collections.ArrayList

class NewsAdapter constructor(var context: Context, var listNews: ArrayList<ItemNewsModel>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    val formatDate = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.apply {
            newsContent.setText(listNews[position].newsContent)
            newsTitle.setText(listNews[position].newsTitle)
            newsLike.setImageResource(listNews[position].newsLike)
            Glide.with(context).load(R.mipmap.ic_launcher).into(newsPhoto)
            val time = SimpleDateFormat(formatDate).parse(listNews[position].newsTime).time
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

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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