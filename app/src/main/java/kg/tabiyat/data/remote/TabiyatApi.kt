package kg.tabiyat.data.remote

import android.net.Uri
import kg.tabiyat.data.model.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface TabiyatApi {
    @POST("api/v1/register")
    @Headers("Accept-Language: ru", "Content-Type: application/json", "Accept: application/json")
    suspend fun createUser(@Body signUpModel: SignUpModel): UserExample

    @POST("api/v1/register")
    @Headers("Accept-Language: ru", "Content-Type: application/json", "Accept: application/json")
    suspend fun createGmailUser(@Body type: String, email: String): UserExample

    @PUT("api/v1/customer/update")
    @Headers("Accept: application/json")
    suspend fun updateName(
        @Header("Authorization") token: String,
        @Query("name") name: String
    ): UpdateUserData

    @Multipart
    @POST("api/v1/customer/avatar")
    @Headers("Accept: application/json")
    suspend fun updateAvatar(
        @Header("Authorization") token: String,
        @Part avatar:MultipartBody.Part,
       // @Query("avatar") uri: String
    ): UserExample

    @POST("api/v1/login")
    @Headers("Accept-Language: ru", "Content-Type: application/json", "Accept: application/json")
    suspend fun loginUser(@Body loginModel: LoginModel): UserExample

    @POST("api/v1/favorites")
    suspend fun createFavorite(
        @Header("Authorization") token: String,
        @Body favoriteModel: FavoriteModel
    ): FavoriteExample

    @POST("api/v1/observations")
    @Headers("Accept: application/json")
    suspend fun postObservation(
        @Header("Authorization") token: String,
        @Body postObserve: PostObserve
    ): ObservationModel

    @GET("api/v1/me")
    @Headers("Accept: application/json")
    suspend fun getUserData(@Header("Authorization") token: String): UserExample

    @GET("api/v1/favorites")
    @Headers("Accept: application/json")
    suspend fun getFavoritesList(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): FavoriteExample

    @DELETE("api/v1/favorites/{id}")
    suspend fun deleteFavorite(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeleteStatus

    @GET("api/v1/plants")
    suspend fun getPlantsList(@Query("page") page: Int): Example

    @GET("api/v1/animals")
    suspend fun getAnimalsList(@Query("page") page: Int): Example

    @GET("api/v1/information")
    suspend fun getInfoList(@Query("page") page: Int): Example

    @GET("api/v1/about")
    suspend fun getAboutProject(): AboutModel


}