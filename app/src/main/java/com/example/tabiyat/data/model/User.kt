package com.example.tabiyat.data.model

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
class UserExample {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("data")
    @Expose
    var data: UserData? = null
}

class UserData {
    @SerializedName("customer")
    @Expose
    var customer: Customer? = null
}
class Customer {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("locale")
    @Expose
    var locale: String? = null

    @SerializedName("fullName")
    @Expose
    var fullName: Any? = null

    @SerializedName("avatar")
    @Expose
    var avatar: Any? = null

    @SerializedName("authType")
    @Expose
    var authType: String? = null

    @SerializedName("identification")
    @Expose
    var identification: String? = null

    @SerializedName("apiToken")
    @Expose
    var apiToken: String? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: CreatedAt? = null

    @SerializedName("updatedAt")
    @Expose
    var updatedAt: UpdatedAt? = null
}
class UpdatedAt {
    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("timezone_type")
    @Expose
    var timezoneType: Int? = null

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null
}
class CreatedAt {
    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("timezone_type")
    @Expose
    var timezoneType: Int? = null

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null
}


