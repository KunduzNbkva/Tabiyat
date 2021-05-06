package com.example.tabiyat.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>( binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T,clickListener: OnItemClickListener)
}