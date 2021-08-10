package ar.com.wolox.android.example.ui.viewpager.detailnews
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentDetailBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class DetailFragment constructor() : WolmoFragment<FragmentDetailBinding, DetailPresenter>(), DetailView {

    override fun layout() = R.layout.fragment_detail
    override fun init() {
    }
    companion object {
        fun newInstance() = DetailFragment()
    }
}