package ar.com.wolox.android.example.ui.viewpager.news

import ar.com.wolox.android.example.model.responses.Page

interface NewsView {
    fun showNewsList(listNews: ArrayList<Page>)
    fun showEmptyNews()
    fun refreshView()
    fun toast(string: String)
}