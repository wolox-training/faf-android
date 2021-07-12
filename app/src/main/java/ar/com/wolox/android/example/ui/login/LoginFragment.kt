package ar.com.wolox.android.example.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.core.widget.addTextChangedListener
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.android.example.ui.hompage.HomePageActivity
import ar.com.wolox.android.example.ui.signup.SignUpActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.openBrowser

class LoginFragment private constructor() : WolmoFragment<FragmentLoginBinding, LoginPresenter>(), LoginView {

    override fun layout() = R.layout.fragment_login

    override fun init() {
    }

    override fun setListeners() {
        with(binding) {

            editTextEmail.addTextChangedListener {}
            editTextPassword.addTextChangedListener {}

            buttonLogin.setOnClickListener {
                val etEmail = editTextEmail
                val etPassword = editTextPassword
                presenter.onClickLogin(editTextEmail.text.toString(), editTextPassword.text.toString(), etEmail, etPassword)
            }
            buttonSignup.setOnClickListener { presenter.onClickSingup() }
            textViewTerms.setOnClickListener { presenter.onClickTerms() }
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun goToHomePage() {
        HomePageActivity.start(requireContext())
        val sharedPref: SharedPreferences? = activity?.getSharedPreferences(R.string.prefs_name.toString(), Context.MODE_PRIVATE)
        if (sharedPref != null) {
            sharedPref.edit().putString(R.string.login.toString(), "login").apply()
            sharedPref.edit().putBoolean(R.string.state.toString(), true).apply()
        }
    }
    override fun goToSignUp() = SignUpActivity.start(requireContext())
    override fun goToWeb(url: String) = requireContext().openBrowser(url)
}