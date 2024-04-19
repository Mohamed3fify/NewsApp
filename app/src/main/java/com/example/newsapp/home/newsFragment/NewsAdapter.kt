package com.example.newsapp.home.newsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsapp.R

import com.example.newsapp.api.model.newsResponse.Article
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdapter(var newsList:List<Article?>?) : Adapter<NewsAdapter.NewViewHolder>() {

    class NewViewHolder(val itemBinding: ItemNewsBinding) : ViewHolder(itemBinding.root){
        fun bind(news: Article?) {
         itemBinding.title.text = news?.title
         itemBinding.author.text = news?.author
         itemBinding.date.text = news?.publishedAt
            Glide.with(itemView)
                .load(news?.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(itemBinding.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val itemBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return NewViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = newsList?.size?:0

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.bind(newsList?.get(position))
    }

    fun changeDate(articles: List<Article?>?) {
      newsList = articles
        notifyDataSetChanged()
    }
}