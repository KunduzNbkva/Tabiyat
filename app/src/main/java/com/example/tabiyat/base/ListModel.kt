package com.example.tabiyat.base.uiModels

data class ListModel(
    val srcImage: Int,
    val nameTxt: String,
    val count: String
)

data class PlantsModel(
    var img: Int,
    var title: String,
    var department:String,
)

data class HeaderModel(
    var img: Int,
    var title: String,
    var count: String?
)
