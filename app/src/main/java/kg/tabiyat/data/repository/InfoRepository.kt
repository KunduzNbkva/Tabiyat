package kg.tabiyat.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import kg.tabiyat.data.model.Resource
import kg.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers

class InfoRepository(var api: TabiyatApi) {

    suspend fun getInfoList(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = api.getInfoList(page)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "getInfoList: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }
}