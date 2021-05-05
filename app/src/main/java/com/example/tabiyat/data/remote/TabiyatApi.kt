package com.example.tabiyat.data.remote

import com.example.tabiyat.data.model.SignUpModel
import com.example.tabiyat.data.model.User
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TabiyatApi {

    @POST("api/v1/register")
    @Headers("Accept-Language: ru","Content-Type: application/json","Accept: application/json")
    suspend fun createUser(@Body signUpModel: SignUpModel): User

}