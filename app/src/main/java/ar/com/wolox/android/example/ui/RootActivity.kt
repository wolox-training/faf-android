package ar.com.wolox.android.example.ui

import android.content.Context
import android.content.SharedPreferences
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityRootBinding
import ar.com.wolox.android.example.ui.hompage.HomePageFragment
import ar.com.wolox.android.example.ui.login.LoginFragment
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class RootActivity : WolmoActivity<ActivityRootBinding>() {
    override fun layout() = R.layout.activity_root

    override fun init() {
        val sharedPref: SharedPreferences? = getSharedPreferences(getString(R.string.prefs_name), Context.MODE_PRIVATE)
        val state = sharedPref?.getBoolean(Extras.UserLogin.ISLOGIN, false)
        if (state == true) {
            replaceFragment(binding.activityRootContent.id, HomePageFragment.newInstance())
        } else {
            replaceFragment(binding.activityRootContent.id, LoginFragment.newInstance())
        }
    }
}