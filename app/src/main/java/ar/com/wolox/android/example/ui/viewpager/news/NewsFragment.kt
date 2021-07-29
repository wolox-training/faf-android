package ar.com.wolox.android.example.ui.viewpager.news

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsBinding
import ar.com.wolox.android.example.model.ItemNewsModel
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<FragmentNewsBinding, NewsPresenter>(), NewsView {

    override fun layout() = R.layout.fragment_news

    override fun init() {
        presenter.setListNews()
    }

    override fun setListeners() {
        binding.newsSwipeToRefresh.setOnRefreshListener {
            presenter.refreshNews()
        }
    }

    override fun loadRecyclerView(listNews: ArrayList<ItemNewsModel>) {
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.newsRecyclerView.adapter = NewsAdapter(requireContext(), listNews)
    }

    override fun showEmptyNews() {
        binding.imageViewEmptyNews.visibility = View.VISIBLE
        binding.textViewEmptyNews.visibility = View.VISIBLE
    }
    override fun refreshView() {
        presenter.setListNews()
        binding.newsSwipeToRefresh.isRefreshing = false
    }
}
