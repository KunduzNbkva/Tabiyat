package kg.tabiyat.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import kg.tabiyat.App
import kg.tabiyat.data.model.Resource
import kg.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody

class ProfileRepository(val api: TabiyatApi) {

    suspend fun updateAvatar(uri: MultipartBody.Part) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val token = "Bearer ${App.prefs!!.getAuthToken()}"
            val request = api.updateAvatar(token, uri)
            emit(Resource.success(data = request))
            App.prefs!!.saveUser(request.data!!.customer, "customer")
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "updateAvatar: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }


    suspend fun getUser() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val token = "Bearer ${App.prefs!!.getAuthToken()}"
            val request = api.getUserData(token)
            emit(Resource.success(data = request))
            App.prefs!!.saveUser(request.data!!.customer, "customer")
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "getUser: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }

    fun updateUserName(name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val token = "Bearer ${App.prefs!!.getAuthToken()}"
            val request = api.updateName(token, name)
            emit(Resource.success(data = request))
            App.prefs!!.saveUser(request.data!!.customer, "customer")
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "updateUserName: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }

}