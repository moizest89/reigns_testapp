package com.moizest89.reign.apptest.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moizest89.reign.apptest.databinding.ItemNewsListBinding

class MainAdapter() : RecyclerView.Adapter<MainAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: Holder, position: Int) {

    }

    override fun getItemCount(): Int  = 10

    inner class Holder(itemView: ItemNewsListBinding) : RecyclerView.ViewHolder(itemView.root)
}