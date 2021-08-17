package ar.com.wolox.android.example.ui.viewpager.detailnews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityLoginBinding
import ar.com.wolox.android.example.utils.Extras.ViewPager.DETAIL_BUNDLE
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class DetailActivity : WolmoActivity<ActivityLoginBinding>() {
    override fun layout() = R.layout.activity_login
    override fun init() {
        replaceFragment(binding.activityLoginContent.id, DetailFragment.newInstance(requireArgument(DETAIL_BUNDLE)))
    }
    companion object {
        fun start(context: Context, datos: Bundle) {
            with(Intent(context, DetailActivity::class.java)) {
                putExtra(DETAIL_BUNDLE, datos)
                context.startActivity(this)
            }
        }
    }
}
