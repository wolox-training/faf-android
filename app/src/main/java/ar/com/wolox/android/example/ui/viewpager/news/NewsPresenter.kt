package ar.com.wolox.android.example.ui.viewpager.news

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
    private var respuesta = ArrayList<Page>()
    fun onInit() = launch {
        countNews += 10
        networkRequest(postRepository.getAllNews()) {
                onResponseSuccessful { response ->
                    next_page = response!!.next_page
                    respuesta = response.page as ArrayList<Page>
                    if (respuesta != null) {
                        for (i in 0..countNews) {
                            list.add(respuesta[i])
                        }
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
    fun loadNextNews() = launch {
        countNews += 10
        if (countNews < 20) {
            for (i in countNews - offset..countNews) {
                list.add(respuesta[i])
            }
            view?.showMoreNewsList(list)
        } else {
            countNews = 10
            networkRequest(postRepository.getNextPageNews(next_page)) {
                onResponseSuccessful { response ->
                    next_page = response!!.next_page
                    respuesta = response.page as ArrayList<Page>
                    if (respuesta != null) {
                        for (i in 0..countNews) {
                            list.add(respuesta[i])
                        }
                    }
                    view?.showMoreNewsList(list)
                }
                onResponseFailed { _, _ -> view?.toast("Error en el servidor") }
                onCallFailure { view?.toast("Error en la conexion") }
            }
        }
    }
}