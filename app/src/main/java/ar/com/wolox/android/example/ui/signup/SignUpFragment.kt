package ar.com.wolox.android.example.ui.signup

import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentSignUpBinding
import ar.com.wolox.android.example.ui.login.LoginActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class SignUpFragment private constructor() : WolmoFragment<FragmentSignUpBinding, SignUpPresenter>(), SignUpView {

    override fun layout() = R.layout.fragment_sign_up
    override fun init() {
    }
    companion object {
        fun newInstance() = SignUpFragment()
    }

    override fun onBackPressed(): Boolean {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        return super.onBackPressed()
    }
}
