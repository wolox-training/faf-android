package ar.com.wolox.android.example.ui.hompage

import androidx.viewpager.widget.ViewPager
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentHomePageBinding
import ar.com.wolox.android.example.ui.viewpager.news.NewsFragment
import ar.com.wolox.android.example.ui.viewpager.profile.ProfileFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.google.android.material.tabs.TabLayout
import dagger.Lazy
import javax.inject.Inject

class HomePageFragment private constructor() : WolmoFragment<FragmentHomePageBinding, HomePagePresenter>(), HomePageView {

    @Inject
    internal lateinit var newsFragment: Lazy<NewsFragment>

    @Inject
    internal lateinit var profileFragment: ProfileFragment

    lateinit var tabLayout: TabLayout

    override fun layout() = R.layout.fragment_home_page
    override fun init() {
        tabLayout = binding.tabLayout
        binding.homeViewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                newsFragment.get() to "news",
                profileFragment to "profile")
        }
        tabLayout.setupWithViewPager(binding.homeViewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_news_icons)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_profile_icon)
    }

    override fun setListeners() {
        binding.homeViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    companion object {
        fun newInstance() = HomePageFragment()
    }
}