package kg.tabiyat.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class AboutModel(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("information")
    @Expose
    var information: Information? = null
)

class Information {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("description")
    @Expose
    var description: Description? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: Any? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}