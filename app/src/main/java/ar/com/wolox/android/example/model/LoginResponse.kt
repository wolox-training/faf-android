package ar.com.wolox.android.example.model
import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("data")val data: Data)

data class Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("created_at")
    val created_at: String
)