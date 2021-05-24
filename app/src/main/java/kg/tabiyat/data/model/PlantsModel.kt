package kg.tabiyat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kg.tabiyat.data.local.db.entity.PlantsEntity
import java.io.Serializable


class Example(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("data")
    @Expose
    var data: Data? = null
)

class Data(
    @SerializedName("plants")
    @Expose
    var plants: Plants? = null
)

class Datum(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: Name? = null,

    //вымирание
    @SerializedName("iucn")
    @Expose
    var iucn: Int? = null,

    @SerializedName("abundance")
    @Expose
    var abundance: Any? = null,

    @SerializedName("phenophase_id")
    @Expose
    var phenophaseId: Any? = null,

    @SerializedName("description")
    @Expose
    var description: Description? = null,

    @SerializedName("url_pick")
    @Expose
    var urlPick: Any? = null,

    @SerializedName("red_book")
    @Expose
    var redBook: Int? = null,

    @SerializedName("division_id")
    @Expose
    var divisionId: Int? = null,

    @SerializedName("class_id")
    @Expose
    var classId: Int? = null,

    @SerializedName("order_id")
    @Expose
    var orderId: Int? = null,

    @SerializedName("family_id")
    @Expose
    var familyId: Int? = null,

    @SerializedName("genus_id")
    @Expose
    var genusId: Int? = null,

    @SerializedName("species_id")
    @Expose
    var speciesId: Int? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,

    var isFavorite: Boolean = false,
) : Serializable


class Description(
    @SerializedName("ru")
    @Expose
    var ru: Any? = null
)


class Link(
    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("label")
    @Expose
    var label: String? = null,

    @SerializedName("active")
    @Expose
    var active: Boolean? = null
)


class Name(
    @SerializedName("ky")
    @Expose
    var ky: String? = null,

    @SerializedName("la")
    @Expose
    var la: String? = null,

    @SerializedName("ru")
    @Expose
    var ru: String? = null
)

class Plants(
    @SerializedName("current_page")
    @Expose
    var currentPage: Int,

    @SerializedName("data")
    @Expose
  //TODO  var data: List<PlantsEntity>? = null,
    var data: List<Datum>,

    @SerializedName("first_page_url")
    @Expose
    var firstPageUrl: String,

    @SerializedName("from")
    @Expose
    var from: Int,

    @SerializedName("last_page")
    @Expose
    var lastPage: Int,

    @SerializedName("last_page_url")
    @Expose
    var lastPageUrl: String,

    @SerializedName("links")
    @Expose
    var links: List<Link>,

    @SerializedName("next_page_url")
    @Expose
    var nextPageUrl: String,

    @SerializedName("path")
    @Expose
    var path: String,

    @SerializedName("per_page")
    @Expose
    var perPage: Int,

    @SerializedName("prev_page_url")
    @Expose
    var prevPageUrl: Any,

    @SerializedName("to")
    @Expose
    var to: Int,

    @SerializedName("total")
    @Expose
    var total: Int
)