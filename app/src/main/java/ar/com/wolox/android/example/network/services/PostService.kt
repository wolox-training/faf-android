package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.LoginRequest
import ar.com.wolox.android.example.model.LoginResponse
import ar.com.wolox.android.example.model.Post
import ar.com.wolox.android.example.model.responses.LikeResponse
import ar.com.wolox.android.example.model.responses.NewsResponse
import ar.com.wolox.android.example.model.responses.Page
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.Query

interface PostService {

    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Response<Post>
    @POST("auth/sign_in")
    suspend fun doLogin(@Body loguinRequest: LoginRequest): Response<LoginResponse>
    @GET("comments")
    suspend fun getNews(@Query("page") page: Int? = null): Response<NewsResponse>
    @PUT("comments/like/{idLike}")
    suspend fun doLike(@Path("idLike") idLike: Int): Response<LikeResponse>
    @GET("comments/{idnews}")
    suspend fun getNewsById(@Path("idnews") idnews: Int): Response<Page>
}
