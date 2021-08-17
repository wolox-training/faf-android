package ar.com.wolox.android.example.ui.viewpager.detailnews

import ar.com.wolox.android.example.model.responses.Page

interface DetailView {
    fun closeSwipeRefresh()
    fun showRefreshDataNews(page: Page)
    fun backToHome()
    fun showError(string: String)
}