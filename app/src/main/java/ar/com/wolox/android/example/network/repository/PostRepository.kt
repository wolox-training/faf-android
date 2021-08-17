package ar.com.wolox.android.example.network.repository

import ar.com.wolox.android.example.model.LoginRequest
import ar.com.wolox.android.example.network.services.PostService
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import ar.com.wolox.wolmo.networking.retrofit.handler.NetworkRequestHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(private val retrofitServices: RetrofitServices) {

    private val service: PostService
        get() = retrofitServices.getService(PostService::class.java)

    suspend fun getPostById(id: Int) = withContext(Dispatchers.IO) {
        NetworkRequestHandler.safeApiCall { service.getPostById(id) }
    }
    suspend fun getLogin(loginRequest: LoginRequest) = withContext(Dispatchers.IO) {
        NetworkRequestHandler.safeApiCall { service.doLogin(loginRequest) }
    }
    suspend fun getPageNews(page: Int?) = withContext(Dispatchers.IO) {
        NetworkRequestHandler.safeApiCall { service.getNews(page) }
    }
}