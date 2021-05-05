package com.example.tabiyat.data

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



}