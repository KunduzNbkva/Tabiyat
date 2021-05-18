package com.example.tabiyat.data.repository

import androidx.lifecycle.liveData
import com.example.tabiyat.data.model.Resource
import com.example.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class InfoRepository(var api: TabiyatApi){

    suspend fun getInfoList() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            val request = api.getInfoList()
            emit(Resource.success(data = request))
        }catch (e: Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
}