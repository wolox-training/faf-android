package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityRootBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class LoginActivity : WolmoActivity<ActivityRootBinding>() {

    override fun layout() = R.layout.activity_root

    override fun init() {
        replaceFragment(binding.activityRootContent.id, LoginFragment.newInstance())

    }
}
