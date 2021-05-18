package com.example.tabiyat.base

import com.example.tabiyat.data.model.Datum
import com.example.tabiyat.data.model.Favorite


interface OnItemClickListener {
    fun onItemClicked(model: ListModel)
}
interface OnMainCardClickListener {
    fun onItemClicked(model: ListModel, adapterPosition: Int)
}
interface OnFavoriteClickListener {
    fun onItemClicked(model: Favorite)
}

interface OnDataClickListener {
    fun onItemClicked(model: Datum)
}

interface OnCardClickListener {
    fun onItemClicked(model: String)
}