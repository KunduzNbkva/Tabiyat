package com.example.tabiyat.data.repository

import androidx.lifecycle.liveData
import com.example.tabiyat.App
import com.example.tabiyat.data.model.FavoriteModel
import com.example.tabiyat.data.model.Resource
import com.example.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class FavoriteRepository(var api: TabiyatApi){

    suspend fun createFavorite(favoriteModel: FavoriteModel) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
           var token = "Bearer ${App.prefs!!.getAuthToken()}"
            val request = api.createFavorite(token,favoriteModel)
            emit(Resource.success(data = request))
        }catch (e: Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    suspend fun getFavoritesList(page:Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            var token = "Bearer ${App.prefs!!.getAuthToken()}"
            val request = api.getFavoritesList(token,page)
            emit(Resource.success(data = request))
        }catch (e: Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }


}