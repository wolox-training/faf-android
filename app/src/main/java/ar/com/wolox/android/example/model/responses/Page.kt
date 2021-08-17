package ar.com.wolox.android.example.model.responses

import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("id")
    val id: Int,
    @SerializedName("commenter")
    val commenter: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("likes")
    val likes: List<Int>,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("updated_at")
    val updated_at: String
)