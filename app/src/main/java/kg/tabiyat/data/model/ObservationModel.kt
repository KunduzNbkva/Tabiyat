package kg.tabiyat.data.model

import android.net.Uri
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kg.tabiyat.data.local.db.entity.Abundance
import okhttp3.MultipartBody


data class ObservationModel(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("data")
    @Expose
    var data: ObservationData? = null
)

data class ObservationData(
    @SerializedName("observe")
    @Expose
    var observe: Observe? = null
)

data class Observe(
    @SerializedName("comment")
    @Expose
    var comment: String? = null,

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null,

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null,

    @SerializedName("altitude")
    @Expose
    var altitude: String? = null,

    @SerializedName("uncertainty")
    @Expose
    var uncertainty: String? = null,

    @SerializedName("moderated_by")
    @Expose
    var moderatedBy: String? = null,

    @SerializedName("customer_id")
    @Expose
    var customerId: Int? = null,

    @SerializedName("observationable_id")
    @Expose
    var observationableId: Int? = null,

    @SerializedName("observationable_type")
    @Expose
    var observationableType: String? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null
)


data class PostObserve(
    @SerializedName("observationable_type")
    @Expose
    var observationableType: String? = null,

    @SerializedName("observationable_id")
    @Expose
    var observationableId: Int? = null,

    @SerializedName("comment")
    @Expose
    var comment: String? = null,

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null,

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null,

    @SerializedName("altitude")
    @Expose
    var altitude: Double? = null,

    @SerializedName("uncertainty")
    @Expose
    var accuracy: Float? = null,

    @SerializedName("abundance")
    @Expose
    var abundance: Int? = null,

    @SerializedName("images")
    @Expose
    var images: List<MultipartBody.Part>? = null,

    @SerializedName("moderated_by")
    @Expose
    var moderated:Int? = null

)