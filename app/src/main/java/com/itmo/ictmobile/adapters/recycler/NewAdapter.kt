package com.itmo.ictmobile.adapters.recycler

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.itmo.ictmobile.R
import com.itmo.ictmobile.data.models.ArticlesItem

class NewAdapter : RecyclerView.Adapter<NewAdapter.NewViewHolder>() {

    private var news: List<ArticlesItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_new, parent, false)
        return NewViewHolder(itemView)
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.bind(news[position])
    }

    fun updateNews(newNews: List<ArticlesItem>) {
        news = newNews
        notifyDataSetChanged()
    }

    class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageNew: ImageView = itemView.findViewById(R.id.imageNew)
        private val titleNew: TextView = itemView.findViewById(R.id.titleNew)
        private val authorNew: TextView = itemView.findViewById(R.id.authorNew)

        fun bind(newsResponce: ArticlesItem) {
            titleNew.text = newsResponce.title
            authorNew.text = newsResponce.author

            imageNew.load(newsResponce.urlToImage) {
                crossfade(true)
                placeholder(R.mipmap.ic_launcher)
                transformations(RoundedCornersTransformation(26F))
            }

            itemView.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(newsResponce.url)

                itemView.context.startActivity(i)
            }
        }

    }
}