package com.example.tabiyat.ui.main.tabiyat

import com.example.tabiyat.base.uiModels.ListModel
import com.example.tabiyat.base.uiModels.PlantsModel

interface OnItemClickListener {
    fun onItemClicked(model: ListModel)
}
interface OnPlantsClickListener {
    fun onItemClicked(model: PlantsModel)
}
interface OnCardClickListener {
    fun onItemClicked(model: String)
}