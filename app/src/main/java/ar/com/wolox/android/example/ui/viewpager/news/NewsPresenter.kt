package ar.com.wolox.android.example.ui.viewpager.news

import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.ItemNewsModel
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor() : BasePresenter<NewsView>() {

    fun setListNews() {
        var list = ArrayList<ItemNewsModel>()
        for (num in 1..20) {
            if (num % 2 == 0) {
                list.add(ItemNewsModel(R.drawable.ic_profile_icon, "Ali Connors", "I´ll be in you neighborhood doing errands...", "2021-07-29T11:00:00.000Z", R.drawable.ic_favorite))
            } else {
                list.add(ItemNewsModel(R.drawable.ic_profile_icon, "Ali Connors", "I´ll be in you neighborhood doing errands...", "2021-07-29T09:00:00.000Z", R.drawable.ic_favorite_border))
            }
        }
        if (list.isEmpty()) view?.showEmptyNews()
        view?.loadRecyclerView(list)
    }
    fun refreshNews() {
        view?.refreshView()
    }
}