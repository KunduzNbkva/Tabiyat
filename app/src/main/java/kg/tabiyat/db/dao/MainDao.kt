package kg.tabiyat.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kg.tabiyat.db.entity.PlantsEntity

@Dao
interface MainDao {

    @Query("SELECT * FROM plants_list")
    fun getLocalPlantsList() : LiveData<List<PlantsEntity>>

    @Insert
    fun insertPlantsList(list: List<PlantsEntity>)

}