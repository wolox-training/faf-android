package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.LoginRequest
import ar.com.wolox.android.example.model.LoginResponse
import ar.com.wolox.android.example.model.Post
import ar.com.wolox.android.example.model.responses.NewsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {

    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Response<Post>
    @POST("auth/sign_in")
    suspend fun doLogin(@Body loguinRequest: LoginRequest): Response<LoginResponse>
    @GET("comments")
    suspend fun getnews(): Response<NewsResponse>
}
