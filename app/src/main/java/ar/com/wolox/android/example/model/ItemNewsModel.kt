package ar.com.wolox.android.example.model

data class ItemNewsModel constructor(
    val newPhoto: Int,
    val newsTitle: String,
    val newsContent: String,
    val newsTime: String,
    val newsLike: Int
)