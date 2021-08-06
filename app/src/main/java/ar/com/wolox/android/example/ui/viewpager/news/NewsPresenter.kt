package ar.com.wolox.android.example.ui.viewpager.news

import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.ItemNewsModel
import ar.com.wolox.android.example.model.responses.Page
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.PostRepository
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val postRepository: PostRepository) : CoroutineBasePresenter<NewsView>() {

    fun onInit() = launch {
        var list = ArrayList<Page>()
        networkRequest(postRepository.getAllNews()) {
                onResponseSuccessful { response ->
                    if (response != null) {
                       list  = response.page as ArrayList<Page>
                    }
                    if (list.isEmpty()) {
                        view?.showEmptyNews()
                    } else {
                        view?.showNewsList(list)
                    }
                }
                onResponseFailed { _, _ -> view?.toast("Error en el servidor") }
                onCallFailure { view?.toast("Error en la conexion") }
        }
    }
    fun onSwipeRefresh() {
        view?.refreshView()
    }
}