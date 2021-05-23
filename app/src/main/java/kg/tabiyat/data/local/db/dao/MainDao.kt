package kg.tabiyat.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kg.tabiyat.data.local.db.entity.PlantsEntity

@Dao
interface MainDao {

    @Query("SELECT * FROM plants_list")
    fun getLocalPlantsList() : LiveData<List<PlantsEntity>>

    @Insert
    fun insertPlantsList(list: List<PlantsEntity>)

//    @Query("SELECT * FROM animals_list")
//    fun getLocalPlantsList() : LiveData<List<PlantsEntity>>
//
//    @Insert
//    fun insertPlantsList(list: List<PlantsEntity>)

}