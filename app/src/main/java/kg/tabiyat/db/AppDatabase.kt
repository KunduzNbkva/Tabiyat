package kg.tabiyat.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kg.tabiyat.db.converter.Converters
import kg.tabiyat.db.dao.MainDao
import kg.tabiyat.db.entity.PlantsEntity

@TypeConverters(Converters::class)
@Database(
    entities = [PlantsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}