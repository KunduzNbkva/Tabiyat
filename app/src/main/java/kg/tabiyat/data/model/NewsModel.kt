package kg.tabiyat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class NewsModel(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("news")
    @Expose
    var news: News? = null
)


data class News(
    @SerializedName("current_page")
    @Expose
    var currentPage: Int? = null,

    @SerializedName("data")
    @Expose
    var data: List<NewsData>? = null,

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

data class NewsData (
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("title")
    @Expose
    var title: Name? = null,

    @SerializedName("content")
    @Expose
    var content: Name? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null,

    @SerializedName("published")
    @Expose
    var published: Int? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
) : Serializable





