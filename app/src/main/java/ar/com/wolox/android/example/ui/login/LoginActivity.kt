package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityLoginBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class LoginActivity : WolmoActivity<ActivityLoginBinding>() {

    override fun layout() = R.layout.activity_login

    override fun init() {
        replaceFragment(binding.activityLoginContent.id, LoginFragment.newInstance())
    }
}
