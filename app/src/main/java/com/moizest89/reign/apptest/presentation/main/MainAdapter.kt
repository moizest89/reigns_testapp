package com.moizest89.reign.apptest.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moizest89.reign.apptest.databinding.ItemNewsListBinding
import com.moizest89.reign.apptest.domain.model.NewsItem
import com.chauthai.swipereveallayout.ViewBinderHelper
import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.moizest89.reign.apptest.R
import com.moizest89.reign.apptest.presentation.utils.formatted


class MainAdapter(
    private val data: MutableList<NewsItem>
//) : RecyclerView.Adapter<MainAdapter.Holder>() {
): ListAdapter<NewsItem, MainAdapter.Holder>(DiffUtilCallback()){

    private var onItemClickListener: ((NewsItem, Int) -> Unit?)? = null
    private var onItemDeleted: ((NewsItem, Int) -> Unit?)? = null

    private val viewBinderHelper = ViewBinderHelper()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        viewBinderHelper.bind(holder.swipeRevealLayout, item.id.toString())
        viewBinderHelper.setOpenOnlyOne(true)
        viewBinderHelper.closeLayout(item.id.toString())
        holder.textViewTitle.text = item.title
        holder.textViewSubTitle.text = String.format(
            holder.itemView.context.getString(R.string.news_subtitle_test),
            item.author,
            item.createdAt.formatted()
        )
        holder.linerLayoutItemsContainer.setOnClickListener {
            onItemClickListener?.invoke(item, position)
        }
        holder.swipeRevealLayout.setSwipeListener(object : SwipeRevealLayout.SwipeListener {
            override fun onClosed(view: SwipeRevealLayout?) {
                holder.linerLayoutItemsContainer.setOnClickListener {
                    onItemClickListener?.invoke(item, position)
                }
            }

            override fun onOpened(view: SwipeRevealLayout?) {
                holder.linerLayoutItemsContainer.setOnClickListener(null)
            }

            override fun onSlide(view: SwipeRevealLayout?, slideOffset: Float) {}
        })
        holder.frameLayoutDeleteItem.setOnClickListener {
            onItemDeleted?.invoke(item, position)
        }
    }

    fun isEmpty() = data.isEmpty()

//    fun setData(items: MutableList<NewsItem>) {
//        this.data.clear()
//        this.data.addAll(items)
//        notifyDataSetChanged()
//    }

    fun onItemClickListener(onItemClickListener: (NewsItem, Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    fun onItemDeleted( onItemDeleted: (NewsItem, Int) -> Unit){
        this.onItemDeleted = onItemDeleted
    }

    inner class Holder(itemView: ItemNewsListBinding) : RecyclerView.ViewHolder(itemView.root) {
        var textViewTitle = itemView.textViewTitle
        var textViewSubTitle = itemView.textViewSubTitle
        var swipeRevealLayout = itemView.swipeRevealLayout
        var linerLayoutItemsContainer = itemView.linerLayoutItemsContainer
        var imageViewDeleteItem = itemView.imageViewDeleteItem
        var frameLayoutDeleteItem = itemView.frameLayoutDeleteItem
    }


    fun saveStates(outState: Bundle?) {
        viewBinderHelper.saveStates(outState)
    }

    fun restoreStates(inState: Bundle?) {
        viewBinderHelper.restoreStates(inState)
    }
}

private class DiffUtilCallback : DiffUtil.ItemCallback<NewsItem>(){

    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }
}