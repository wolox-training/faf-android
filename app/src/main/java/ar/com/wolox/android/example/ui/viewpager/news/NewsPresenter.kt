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
    private var respuesta = ArrayList<Page>()
    fun onInit() = launch {
        networkRequest(postRepository.getAllNews()) {
                onResponseSuccessful { response ->
                    next_page = response!!.next_page
                    respuesta = response.page as ArrayList<Page>
                    if (respuesta != null) {
                            list = respuesta
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
        networkRequest(postRepository.getNextPageNews(next_page)) {
            onResponseSuccessful { response ->
                next_page = response!!.next_page
                var lista = ArrayList<Page>()
                for (aux in lista) {
                    list.add(aux)
                }
                view?.showMoreNewsList(list)
            }
            onResponseFailed { _, _ -> view?.toast("Error en el servidor") }
            onCallFailure { view?.toast("Error en la conexion") }
        }
    }
}