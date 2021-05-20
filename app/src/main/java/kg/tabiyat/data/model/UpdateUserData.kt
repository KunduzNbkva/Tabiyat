package kg.tabiyat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateUserData {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("data")
    @Expose
    var data: UpdateData? = null
}

class UpdateData {
    @SerializedName("Message")
    @Expose
    var message: String? = null

    @SerializedName("customer")
    @Expose
    var customer: Customer? = null
}




