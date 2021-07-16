package ar.com.wolox.android.example.ui.hompage

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentHomePageBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class HomePageFragment private constructor() : WolmoFragment<FragmentHomePageBinding, HomePagePresenter>() {

    override fun layout() = R.layout.fragment_home_page
    override fun init() {
    }

    companion object {
        fun newInstance() = HomePageFragment()
    }
}