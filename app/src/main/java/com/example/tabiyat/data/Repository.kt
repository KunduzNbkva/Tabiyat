package com.example.tabiyat.data

import android.util.Log
import androidx.lifecycle.liveData
import com.example.tabiyat.data.model.Resource
import com.example.tabiyat.data.model.SignUpModel
import com.example.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class Repository(var api: TabiyatApi){
    val type: String = "email"
    val email: String = "email@email.ru"
    val password: String = "12345678"


    fun createUser(signUpModel: SignUpModel) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            val request = api.createUser(signUpModel)
            emit(Resource.success(data = request))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    fun getPlantsList() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            val request = api.getPlantsList()
            emit(Resource.success(data = request))
            Log.e("Request","request" + Resource.success(data = request))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e("Request","Error" + Resource.error(data = null, message = e.message ?: "Error"))

        }
    }




}