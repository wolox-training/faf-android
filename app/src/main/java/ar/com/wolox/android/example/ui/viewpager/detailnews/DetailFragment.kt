package ar.com.wolox.android.example.ui.viewpager.detailnews

import android.os.Bundle
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentDetailBinding
import ar.com.wolox.android.example.model.responses.Page
import ar.com.wolox.android.example.utils.Extras.ViewPager.DETAIL_BUNDLE
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.bumptech.glide.Glide

class DetailFragment private constructor() : WolmoFragment<FragmentDetailBinding, DetailPresenter>(), DetailView {

    private val url_photo = "https://ichef.bbci.co.uk/news/1024/branded_mundo/11A42/production/_119885227_messi.jpg"
    var liked: Boolean = false
    override fun layout() = R.layout.fragment_detail
    override fun init() {
        val bundle = arguments?.getBundle(DETAIL_BUNDLE)
        with(binding) {
            textViewDetailContent.text = bundle?.getString(getString(R.string.comment))
            textViewDetailTitle.text = bundle?.getString(getString((R.string.commenter)))
            textViewDetailTime.text = bundle?.getString(getString(R.string.time_ago))
            liked = bundle?.getBoolean(getString(R.string.is_news_liked)) == true
            if (liked == true) {
                imageViewDetailLike.setImageResource(R.drawable.ic_favorite)
            } else {
                imageViewDetailLike.setImageResource(R.drawable.ic_favorite_border)
            }
            Glide.with(requireContext()).load(url_photo).into(imageViewDetailNews)
        }
    }

    override fun setListeners() {
        val bundle = arguments?.getBundle(DETAIL_BUNDLE)
        val newsid = bundle?.getInt(getString(R.string.news_id))
        with(binding) {
            imageViewDetailLike.setOnClickListener {
                presenter.onClickLike(newsid!!)
                if (!(liked == true)) {
                    imageViewDetailLike.setImageResource(R.drawable.ic_favorite)
                    liked = !liked
                } else {
                    imageViewDetailLike.setImageResource(R.drawable.ic_favorite_border)
                    liked = !liked
                }
            }
            swiperefresh.setOnRefreshListener { presenter.onSwipeToRefresh(newsid!!) }
            toolbar.setNavigationOnClickListener {
                presenter.onBackArrowPressed()
            }
        }
    }
    companion object {
        fun newInstance(dato: Bundle): DetailFragment = DetailFragment().apply {
            val bundle = Bundle()
            bundle.putBundle(DETAIL_BUNDLE, dato)
            arguments = bundle
        }
    }

    override fun closeSwipeRefresh() {
        binding.swiperefresh.isRefreshing = false
    }

    override fun showRefreshDataNews(page: Page) {
        with(binding) {
            textViewDetailContent.text = page.comment
        }
    }
    override fun backToHome() {
        activity?.onBackPressed()
    }
    override fun showError(string: String) { Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show() }
}