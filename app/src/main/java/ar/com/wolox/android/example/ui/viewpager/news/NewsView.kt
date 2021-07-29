package ar.com.wolox.android.example.ui.viewpager.news

import ar.com.wolox.android.example.model.ItemNewsModel

interface NewsView {
    fun loadRecyclerView(listNews: ArrayList<ItemNewsModel>)
    fun showEmptyNews()
    fun refreshView()
}