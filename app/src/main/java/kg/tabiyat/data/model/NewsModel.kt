package kg.tabiyat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("message")
    @Expose
    var message: News? = null
)


data class News (
    @SerializedName("current_page")
    @Expose
    var currentPage: Int? = null,

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null,

    @SerializedName("first_page_url")
    @Expose
    var firstPageUrl: String? = null,

    @SerializedName("from")
    @Expose
    var from: Any? = null,

    @SerializedName("last_page")
    @Expose
    var lastPage: Int? = null,

    @SerializedName("last_page_url")
    @Expose
    var lastPageUrl: String? = null,

    @SerializedName("links")
    @Expose
    var links: List<Link>? = null,

    @SerializedName("next_page_url")
    @Expose
    var nextPageUrl: Any? = null,

    @SerializedName("path")
    @Expose
    var path: String? = null,

    @SerializedName("per_page")
    @Expose
    var perPage: Int? = null,

    @SerializedName("prev_page_url")
    @Expose
    var prevPageUrl: Any? = null,

    @SerializedName("to")
    @Expose
    var to: Any? = null,

    @SerializedName("total")
    @Expose
    var total: Int? = null
)





