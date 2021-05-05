package com.example.tabiyat.data.model

import android.net.Uri
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//"identification":"name@name.ru","password":"12345678","type":"email"
// "customer":{
// "id":3,
// "locale":"ru",
// "fullName":null,
// "avatar":null,
// "authType":"email",
// "identification":"name@name.ru",
// "apiToken":"YDCoocXK0XWm4cZol1Fj0DvACZCd38Z5f7PFGrj45hqVjldeSN7Jexuh1317qp7sv875Zpu2vENRPQ0TzUPMAtiGYg8xNNsqyqCxDyjsdqiWIcNOHrrGAUh19bUJLnOE",
// "createdAt":{"date":"2021-04-25 15:14:55.000000","timezone_type":3,"timezone":"Asia\/Bishkek"},
// "updatedAt":{"date":"2021-04-25 15:14:55.000000","timezone_type":3,"timezone":"Asia\/Bishkek"}}}}


data class User(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("locale")
    @Expose
    val locale: String,
    @SerializedName("fullName")
    @Expose
    var fullName: String,
    @SerializedName("avatar")
    @Expose
    var avatar: Uri,
    @SerializedName("authType")
    @Expose
    val authType: String,
    @SerializedName("apiToken")
    @Expose
    val apiToken: String,
    @SerializedName("createdAt")
    @Expose
    val createdAt: CreatedAt,
    @SerializedName("updatedAt")
    @Expose
    val updatedAt: UpdatedAt
)

data class CreatedAt(
    val date: String,
    val timeZoneType: Int,
    val timeZone: String
)

data class UpdatedAt(
    val date: String,
    val timeZoneType: Int,
    val timeZone: String
)