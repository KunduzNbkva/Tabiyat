package kg.tabiyat.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import kg.tabiyat.App
import kg.tabiyat.data.model.Customer
import kg.tabiyat.data.model.PostObserve
import kg.tabiyat.data.model.Resource
import kg.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers

class ObservationRepository(var api: TabiyatApi) {

    suspend fun postObservation(postObserve: PostObserve) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val token = "Bearer ${App.prefs!!.getAuthToken()}"
            val request = api.postObservation("Bearer $token", postObserve)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "postObservation: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }

    suspend fun postAnimalObservation(postObserve: PostObserve) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val token = "Bearer ${App.prefs!!.getAuthToken()}"
            val request = api.postObservation("Bearer $token", postObserve)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "postObservation: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }
}