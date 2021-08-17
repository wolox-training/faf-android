package ar.com.wolox.android.example.model.responses

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("page")
    val page: List<Page>,
    @SerializedName("count")
    val count: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_count")
    val total_count: Int,
    @SerializedName("current_page")
    val current_page: Int,
    @SerializedName("previous_page")
    val previous_page: String,
    @SerializedName("next_page")
    val next_page: Int,
    @SerializedName("next_page_url")
    val next_page_url: String,
    @SerializedName("previous_page_url") val previous_page_url: String
)
