package kg.tabiyat.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.tabiyat.data.model.Resource
import kg.tabiyat.data.remote.TabiyatApi
import kg.tabiyat.data.local.db.AppDatabase
import kg.tabiyat.data.local.db.entity.PlantsEntity
import kotlinx.coroutines.Dispatchers

class AnimalsRepository(var api: TabiyatApi,var db:AppDatabase) {

    suspend fun getAnimalsList(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = api.getAnimalsList(page)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "getAnimalsList: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }

    fun insertAnimalsList(list: List<PlantsEntity>){
        db.mainDao().insertPlantsList(list)
    }

    fun getLocalAnimalsList() : LiveData<List<PlantsEntity>> {
        return db.mainDao().getLocalPlantsList()
    }

}