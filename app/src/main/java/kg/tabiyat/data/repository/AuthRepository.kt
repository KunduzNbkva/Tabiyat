package kg.tabiyat.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import kg.tabiyat.App
import kg.tabiyat.data.model.Customer
import kg.tabiyat.data.model.LoginModel
import kg.tabiyat.data.model.Resource
import kg.tabiyat.data.model.SignUpModel
import kg.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers

class AuthRepository(var api: TabiyatApi) {

    fun createUser(signUpModel: SignUpModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = api.createUser(signUpModel)
            emit(Resource.success(data = request))
            App.prefs!!.saveUser(request.data!!.customer, "customer")
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "createUser: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }

    fun updateUserName(name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val token = App.prefs!!.getUser<Customer>("customer")!!.apiToken.toString()
            val request = api.updateName(token, name)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "updateUserName: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }

    fun createGmailUser(type: String, email: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = api.createGmailUser(type, email)
            emit(Resource.success(data = request))
            App.prefs!!.saveUser(request.data!!.customer, "customer")
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "createGmailUser: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }

    fun loginUser(loginModel: LoginModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = api.loginUser(loginModel)
            emit(Resource.success(data = request))
            App.prefs!!.saveUser(request.data!!.customer, "customer")
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "loginUser: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }
}