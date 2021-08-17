package ar.com.wolox.android.example.ui.viewpager.news

import ar.com.wolox.android.example.model.responses.NewsResponse
import ar.com.wolox.android.example.model.responses.Page
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.PostRepository
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val postRepository: PostRepository) : CoroutineBasePresenter<NewsView>() {

    var list = ArrayList<Page>()
    var next_page = 0
    var countNews = 0
    val offset = 10
    private var response_news = ArrayList<Page>()
    fun onInit() = launch {
        countNews += 10
        networkRequest(postRepository.getPageNews(null)) {
            onResponseSuccessful { response ->
                responseToListNews(response!!)
            }
            onResponseFailed { _, _ -> view?.toast("Error en el servidor") }
            onCallFailure { view?.toast("Error en la conexion") }
        }
    }
    fun onRefresh() = launch {
        countNews = 10
        networkRequest(postRepository.getPageNews(null)) {
            onResponseSuccessful { response ->
                list.clear()
                next_page = response!!.next_page
                response_news = response.page as ArrayList<Page>
                if (response_news != null) {
                    for (i in 0..countNews) {
                        list.add(response_news[i])
                    }
                }
                view?.refreshView(list)
            }
            onResponseFailed { _, _ -> view?.toast("Error en el servidor") }
            onCallFailure { view?.toast("Error en la conexion") }
        }
    }
    fun loadNextNews() = launch {
        countNews += 10
        if (countNews < 20) {
            for (i in countNews - offset..countNews) {
                list.add(response_news[i])
            }
            view?.showMoreNewsList(list)
        } else {
            countNews = 10
            networkRequest(postRepository.getPageNews(next_page)) {
                onResponseSuccessful { response ->
                    responseToListNews(response!!)
                }
                onResponseFailed { _, _ -> view?.toast("Error en el servidor") }
                onCallFailure { view?.toast("Error en la conexion") }
            }
        }
    }

    private fun responseToListNews(response: NewsResponse) {
        next_page = response!!.next_page
        response_news = response.page as ArrayList<Page>
        if (response_news != null) {
            for (i in 0..countNews) {
                list.add(response_news[i])
            }
        }
        if (list.isEmpty()) {
            view?.showEmptyNews()
        } else {
            view?.showNewsList(list)
        }
    }
}