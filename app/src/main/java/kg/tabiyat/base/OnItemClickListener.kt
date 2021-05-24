package kg.tabiyat.base

import android.net.Uri
import kg.tabiyat.data.local.db.entity.PlantsEntity
import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.NewsData
import kg.tabiyat.databinding.PlantsFragmentBinding
import kg.tabiyat.ui.main.plants.PlantsAdapter


interface OnItemClickListener {
    fun onItemClicked(model: ListModel)
}

interface OnDeleteListener {
    fun onItemClicked(position: Uri)
}

interface OnMainCardClickListener {
    fun onItemClicked(model: ListModel, adapterPosition: Int)
}

interface OnRoomDataClickListener {
    fun onItemClicked(model: PlantsEntity)
}

interface OnNewsClickListener {
    fun onItemClicked(model: NewsData)
}

interface OnDataClickListener {
    fun onItemClicked(model: Datum)
}

interface OnCardClickListener {
    fun onItemClicked(model: String)
}