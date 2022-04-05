package com.example.spaceflightnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spaceflightnews.databinding.NewsPreviewBinding
import com.example.common.domain.NewsResponseItem

/**
 * This is an adapter for the recycler view and it uses diffUtils for the best performance
 */
class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding:NewsPreviewBinding):RecyclerView.ViewHolder(binding.root)

    private val differCallback = object :DiffUtil.ItemCallback<NewsResponseItem>(){
        override fun areItemsTheSame(
            oldItem: NewsResponseItem,
            newItem: NewsResponseItem
        ): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NewsResponseItem,
            newItem: NewsResponseItem
        ): Boolean {
           return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsPreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = differ.currentList[position]
        holder.binding.apply {
            Glide.with(newsImageView.context).load(news.imageUrl).into(newsImageView)
            sourceTV.text = news.newsSite
            titleTV.text = news.title
            publishedTV.text = news.publishedAt
            setOnClickListener {
                onItemClickListener?.let { it(news) }
            }
        }
    }

    private var onItemClickListener:((NewsResponseItem)->Unit)? = null

    fun setOnClickListener(listener:(NewsResponseItem)->Unit){
        onItemClickListener = listener
    }


}