package com.example.wirda_go.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wirda_go.Data.Model.NewsItem
import com.example.wirda_go.databinding.ItemNewsBinding

class NewsAdapter(private val items: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvNewsTitle.text = item.title
        holder.binding.tvNewsDate.text = item.publishedAt

        Glide.with(holder.itemView.context)
            .load(item.urlToImage)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.binding.imgNewsThumbnail)
    }

    override fun getItemCount(): Int = items.size
}