package com.example.tabiyat.base

data class ListModel(
    val srcImage: Int,
    val nameTxt: String,
    val count: String
)

data class InfoModel(
    val srcImage: Int,
    val nameTxt: String,
)

data class Plant(
    var img: Int,
    var title: String,
    var department:String,
)

data class HeaderModel(
    var img: Int,
    var title: String,
    var count: String?
)
