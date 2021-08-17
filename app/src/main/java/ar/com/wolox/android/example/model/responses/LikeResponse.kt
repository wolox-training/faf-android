package ar.com.wolox.android.example.model.responses

import com.google.gson.annotations.SerializedName

data class LikeResponse(
    @SerializedName("message")
    val message: String
)