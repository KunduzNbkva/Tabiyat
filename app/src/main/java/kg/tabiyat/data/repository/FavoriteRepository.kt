package kg.tabiyat.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import kg.tabiyat.App
import kg.tabiyat.data.model.Customer
import kg.tabiyat.data.model.FavoriteModel
import kg.tabiyat.data.model.Resource
import kg.tabiyat.data.remote.TabiyatApi
import kotlinx.coroutines.Dispatchers

class FavoriteRepository(var api: TabiyatApi) {

    suspend fun createFavorite(favoriteModel: FavoriteModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val token = "Bearer ${App.prefs!!.getUser<Customer>("customer")!!.apiToken}"
            val request = api.createFavorite(token, favoriteModel)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "createFavorite: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }


    suspend fun deleteFavorite(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val token = "Bearer ${App.prefs!!.getUser<Customer>("customer")!!.apiToken}"
            val request = api.deleteFavorite(token, id)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "deleteFavorite: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }

    suspend fun getFavoritesList(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val token = "Bearer ${App.prefs!!.getUser<Customer>("customer")!!.apiToken}"
            val request = api.getFavoritesList(token, page)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "getFavoritesList: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }


}