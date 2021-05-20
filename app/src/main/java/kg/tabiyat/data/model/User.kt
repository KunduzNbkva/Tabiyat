package kg.tabiyat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserExample(
    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("data")
    @Expose
    var data: UserData? = null
)

data class UserData(
    @SerializedName("customer")
    @Expose
    var customer: Customer? = null
)

data class Customer(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("locale")
    @Expose
    var locale: String? = null,

    @SerializedName("fullName")
    @Expose
    var fullName: Any? = null,

    @SerializedName("avatar")
    @Expose
    var avatar: Any? = null,

    @SerializedName("authType")
    @Expose
    var authType: String? = null,

    @SerializedName("identification")
    @Expose
    var identification: String? = null,

    @SerializedName("apiToken")
    @Expose
    var apiToken: String? = null,

    @SerializedName("createdAt")
    @Expose
    var createdAt: CreatedAt? = null,

    @SerializedName("updatedAt")
    @Expose
    var updatedAt: UpdatedAt? = null
) : Serializable

data class UpdatedAt(
    @SerializedName("date")
    @Expose
    var date: String? = null,

    @SerializedName("timezone_type")
    @Expose
    var timezoneType: Int? = null,

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null
)

data class CreatedAt(
    @SerializedName("date")
    @Expose
    var date: String? = null,

    @SerializedName("timezone_type")
    @Expose
    var timezoneType: Int? = null,

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null
)


