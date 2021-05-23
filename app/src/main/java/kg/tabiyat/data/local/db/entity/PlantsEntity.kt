package kg.tabiyat.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kg.tabiyat.data.model.Description
import kg.tabiyat.data.model.Name
import java.io.Serializable

@Entity(tableName = "plants_list")
data class PlantsEntity(
    @PrimaryKey
    var id: Int? = null,
    var name: Name? = null,
    var iucn: Int? = null,
    var abundance: Abundance? = null,
    var phenophaseId: String? = null,
    var description: Description? = null,
    var urlPick: String? = null,
    var redBook: Int? = null,
    var divisionId: Int? = null,
    var classId: Int? = null,
    var orderId: Int? = null,
    var familyId: Int? = null,
    var genusId: Int? = null,
    var speciesId: Int? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var isFavorite: Boolean = false,
) : Serializable


data class Abundance(
    var seldom: String? = null,
    var normally: String? = null,
    var ofter: String? = null,
) : RoomDatabaseEntity(), Serializable