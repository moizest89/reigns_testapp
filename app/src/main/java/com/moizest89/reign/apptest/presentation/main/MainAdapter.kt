package com.moizest89.reign.apptest.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moizest89.reign.apptest.R
import com.moizest89.reign.apptest.databinding.ItemNewsListBinding
import com.moizest89.reign.apptest.domain.model.NewsItem
import com.moizest89.reign.apptest.presentation.utils.formatted

class MainAdapter(
    private val data: MutableList<NewsItem>
) : RecyclerView.Adapter<MainAdapter.Holder>() {

    private var onItemClickListener: ((NewsItem, Int) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = data[position]
        holder.textViewTitle.text = item.title
        holder.textViewSubTitle.text = String.format(
            holder.itemView.context.getString(R.string.news_subtitle_test),
            item.author,
            item.createdAt.formatted()
        )
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item, position)
        }
    }

    fun isEmpty() = data.isEmpty()

    fun setData(items: MutableList<NewsItem>) {
        this.data.clear()
        this.data.addAll(items)
        notifyDataSetChanged()
    }

    fun onItemClickListener(onItemClickListener: (NewsItem, Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = data.size

    inner class Holder(itemView: ItemNewsListBinding) : RecyclerView.ViewHolder(itemView.root) {
        var textViewTitle = itemView.textViewTitle
        var textViewSubTitle = itemView.textViewSubTitle
    }
}