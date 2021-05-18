package com.example.tabiyat.data.remote

import com.example.tabiyat.data.model.*
import retrofit2.http.*

interface TabiyatApi {
    @POST("api/v1/register")
    @Headers("Accept-Language: ru","Content-Type: application/json","Accept: application/json")
    suspend fun createUser(@Body signUpModel: SignUpModel): UserExample

    @POST("api/v1/login")
    @Headers("Accept-Language: ru","Content-Type: application/json","Accept: application/json")
    suspend fun loginUser(@Body loginModel: LoginModel): UserExample

    @POST("api/v1/favorites")
    suspend fun createFavorite(@Header("Authorization")token: String,@Body favoriteModel: FavoriteModel):FavoriteExample

    @GET("api/v1/favorites")
    @Headers("Accept: application/json")
    suspend fun getFavoritesList(@Header("Authorization")token: String,@Query("page") page:Int):FavoriteExample

    @GET("api/v1/plants")
    suspend fun getPlantsList(@Query("page") page:Int):Example

    @GET("api/v1/animals")
    suspend fun getAnimalsList(@Query("page") page:Int):Example

    @GET("api/v1/information")
    suspend fun getInfoList():Example


}