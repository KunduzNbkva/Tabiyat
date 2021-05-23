package kg.tabiyat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kg.tabiyat.data.local.db.entity.PlantsEntity
import java.io.Serializable

class DeleteStatus(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("message")
    @Expose
    var message: String? = null
)

data class FavoriteModel(
    @SerializedName("favoritable_type")
    @Expose
    var favoriteType: String,
    @SerializedName("favoritable_id")
    @Expose
    var favoriteId: Int
)

class FavoriteExample {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: DataFavorite? = null
}


class DataFavorite {
    @SerializedName("favorites")
    @Expose
    var favorites: List<Favorite>? = null
}

class Favorite : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("customer_id")
    @Expose
    var customerId: Int? = null

    @SerializedName("favoritable_type")
    @Expose
    var favoritableType: String? = null

    @SerializedName("favoritable_id")
    @Expose
    var favoritableId: Int? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("favoritable")
    @Expose
     val favoritable: Datum? = null
    // val favoritable: PlantsEntity? = null
}
