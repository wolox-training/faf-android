package ar.com.wolox.android.example.ui.viewpager.detailnews

import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.PostRepository
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val postRepository: PostRepository) : CoroutineBasePresenter<DetailView>() {
    fun onClickLike(likeId: Int) = launch {
        networkRequest(postRepository.likeNews(likeId)) {
            onResponseSuccessful {
            }
            onResponseFailed { _, _ ->
                view?.showError("Error en el servidor")
            }
            onCallFailure {
                view?.showError("Error en la conxion a internet")
            }
        }
    }
    fun onSwipeToRefresh(idnews: Int) = launch {
        networkRequest(postRepository.getNewsById(idnews)) {
            onResponseSuccessful { response ->
                view?.showRefreshDataNews(response!!)
                view?.closeSwipeRefresh()
            }
            onResponseFailed { _, _ ->
                view?.closeSwipeRefresh()
                view?.showError("Error en el servidor")
            }
            onCallFailure {
                view?.closeSwipeRefresh()
                view?.showError("Error en la conxion a internet")
            }
        }
    }
    fun onBackArrowPressed() {
        view?.backToHome()
    }
}