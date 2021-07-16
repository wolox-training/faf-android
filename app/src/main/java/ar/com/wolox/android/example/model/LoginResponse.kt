package ar.com.wolox.android.example.model
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val mane: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("created_at")
    val created_at: String
)