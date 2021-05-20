package kg.tabiyat.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import kg.tabiyat.data.model.Resource
import kg.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers

class AboutRepository(val api: TabiyatApi) {

    suspend fun getProjectInfo() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = api.getAboutProject()
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "getProjectInfo: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }
}