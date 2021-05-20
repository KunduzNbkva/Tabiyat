package kg.tabiyat.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import kg.tabiyat.data.model.Resource
import kg.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers

class PlantsRepository(var api: TabiyatApi) {

    suspend fun getPlantsList(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = api.getPlantsList(page)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "getPlantsList: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }
}