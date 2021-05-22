package kg.tabiyat.base

import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.Favorite
import kg.tabiyat.db.entity.PlantsEntity


interface OnItemClickListener {
    fun onItemClicked(model: ListModel)
}

interface OnMainCardClickListener {
    fun onItemClicked(model: ListModel, adapterPosition: Int)
}

interface OnDataClickListener {
    fun onItemClicked(model: PlantsEntity)
}

interface OnCardClickListener {
    fun onItemClicked(model: String)
}