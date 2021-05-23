package kg.tabiyat.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kg.tabiyat.data.local.db.converter.Converters
import kg.tabiyat.data.local.db.dao.MainDao
import kg.tabiyat.data.local.db.entity.PlantsEntity

@TypeConverters(Converters::class)
@Database(
    entities = [PlantsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}