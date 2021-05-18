package com.example.tabiyat.data.repository

import androidx.lifecycle.liveData
import com.example.tabiyat.App
import com.example.tabiyat.data.model.LoginModel
import com.example.tabiyat.data.model.Resource
import com.example.tabiyat.data.model.SignUpModel
import com.example.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class AuthRepository(var api: TabiyatApi) {

    fun createUser(signUpModel: SignUpModel) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            val request = api.createUser(signUpModel)
            emit(Resource.success(data = request))
            App.prefs!!.saveAuthToken(request.data!!.customer!!.apiToken!!)
        }catch (e: Exception){
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    fun loginUser(loginModel: LoginModel) = liveData(Dispatchers.IO){
        emit(Resource.loading(data=null))
        try{
            val request = api.loginUser(loginModel)
            emit(Resource.success(data=request))
            App.prefs!!.saveAuthToken(request.data!!.customer!!.apiToken!!)
        }catch (e: Exception){
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
}