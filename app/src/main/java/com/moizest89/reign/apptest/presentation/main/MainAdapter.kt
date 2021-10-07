package com.moizest89.reign.apptest.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moizest89.reign.apptest.databinding.ItemNewsListBinding
import com.moizest89.reign.apptest.domain.model.NewsItem

class MainAdapter(
    private val data: MutableList<NewsItem>
) : RecyclerView.Adapter<MainAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = data[position]
        holder.textViewTitle.text = item.title
    }

    fun isEmpty() = data.isEmpty()

    fun setData(items: MutableList<NewsItem>) {
        this.data.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    inner class Holder(itemView: ItemNewsListBinding) : RecyclerView.ViewHolder(itemView.root){
        var textViewTitle = itemView.textViewTitle
        var textViewSubTitle = itemView.textViewSubTitle
    }
}