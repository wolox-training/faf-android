package ar.com.wolox.android.example.ui.viewpager.news

import ar.com.wolox.android.example.model.ItemNewsModel

interface NewsView {
    fun showNewsList(listNews: ArrayList<ItemNewsModel>)
    fun showEmptyNews()
    fun refreshView()
}