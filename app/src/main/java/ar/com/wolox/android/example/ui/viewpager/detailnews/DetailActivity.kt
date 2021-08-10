package ar.com.wolox.android.example.ui.viewpager.detailnews

import android.content.Context
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityLoginBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpTo

class DetailActivity : WolmoActivity<ActivityLoginBinding>() {
    override fun layout() = R.layout.activity_login
    override fun init() {
        replaceFragment(binding.activityLoginContent.id, DetailFragment.newInstance())
    }
    companion object {
        fun start(context: Context) = context.jumpTo(DetailActivity::class.java)
    }
}
