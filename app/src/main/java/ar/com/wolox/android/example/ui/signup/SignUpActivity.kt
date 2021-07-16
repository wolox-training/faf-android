package ar.com.wolox.android.example.ui.signup

import android.content.Context
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityLoginBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpTo

class SignUpActivity : WolmoActivity<ActivityLoginBinding>() {
    override fun layout() = R.layout.activity_login
    override fun init() {
        replaceFragment(binding.activityLoginContent.id, SignUpFragment.newInstance())
    }
    companion object {
        fun start(context: Context) = context.jumpTo(SignUpActivity::class.java)
    }
}