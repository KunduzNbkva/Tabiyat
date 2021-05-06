package com.example.tabiyat.base


interface OnItemClickListener {
    fun onItemClicked(model: ListModel)
}
interface OnMainCardClickListener {
    fun onItemClicked(model: ListModel,adapterPosition: Int)
}
interface OnPlantsClickListener {
    fun onItemClicked(model: Plant)
}
interface OnCardClickListener {
    fun onItemClicked(model: String)
}