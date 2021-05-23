package kg.tabiyat.base

import android.net.Uri
import kg.tabiyat.data.local.db.entity.PlantsEntity
import kg.tabiyat.data.model.Datum


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


interface OnDataClickListener {
    fun onItemClicked(model: Datum)
}

interface OnCardClickListener {
    fun onItemClicked(model: String)
}