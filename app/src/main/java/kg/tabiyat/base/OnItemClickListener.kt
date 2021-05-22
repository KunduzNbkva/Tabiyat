package kg.tabiyat.base

import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.Favorite


interface OnItemClickListener {
    fun onItemClicked(model: ListModel)
}

interface OnMainCardClickListener {
    fun onItemClicked(model: ListModel, adapterPosition: Int)
}

interface OnDataClickListener {
    fun onItemClicked(model: Datum)
}

interface OnCardClickListener {
    fun onItemClicked(model: String)
}