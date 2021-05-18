package com.example.tabiyat.data.repository

import androidx.lifecycle.liveData
import com.example.tabiyat.data.model.Resource
import com.example.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class AnimalsRepository(var api: TabiyatApi){

    suspend fun getAnimalsList(page: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            val request = api.getAnimalsList(page)
            emit(Resource.success(data = request))
        }catch (e: Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
}