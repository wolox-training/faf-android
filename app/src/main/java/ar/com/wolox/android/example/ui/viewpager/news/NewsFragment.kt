package ar.com.wolox.android.example.ui.viewpager.news

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsBinding
import ar.com.wolox.android.example.model.responses.Page
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<FragmentNewsBinding, NewsPresenter>(), NewsView {

    override fun layout() = R.layout.fragment_news

    override fun init() {
        presenter.onInit()
    }

    override fun setListeners() {
        binding.newsSwipeToRefresh.setOnRefreshListener {
            presenter.onSwipeRefresh()
        }
        binding.newsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    presenter.loadNextNews()
                }
            }
        })
    }

    override fun showNewsList(listNews: ArrayList<Page>) {
        with(binding) {
            newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.newsRecyclerView.adapter = NewsAdapter(requireContext(), listNews)
        }
    }
    override fun showEmptyNews() {
        with(binding) {
            imageViewEmptyNews.visibility = View.VISIBLE
            textViewEmptyNews.visibility = View.VISIBLE
        }
    }
    override fun refreshView() {
        presenter.onInit()
        binding.newsSwipeToRefresh.isRefreshing = false
    }

    override fun toast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_LONG).show()
    }

    override fun showMoreNewsList(listNews: ArrayList<Page>) {
        with(binding) {
            newsRecyclerView.adapter = NewsAdapter(requireContext(), listNews)
            newsRecyclerView.adapter?.notifyDataSetChanged()
        }
    }
}
