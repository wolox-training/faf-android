package ar.com.wolox.android.example.ui.login

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.android.example.ui.signup.SignUpActivity
import ar.com.wolox.android.example.ui.viewpager.homeviewpager.HomeViewPagerActivity
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.openBrowser

class LoginFragment private constructor() : WolmoFragment<FragmentLoginBinding, LoginPresenter>(), LoginView {

    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var progressDialog: ProgressDialog

    override fun layout() = R.layout.fragment_login

    override fun init() {
        etEmail = binding.editTextEmail
        etPassword = binding.editTextPassword
    }

    override fun setListeners() {
        with(binding) {

            editTextEmail.addTextChangedListener {}
            editTextPassword.addTextChangedListener {}

            buttonLogin.setOnClickListener {
                presenter.onClickLogin(editTextEmail.text.toString(), editTextPassword.text.toString())
            }
            buttonSignup.setOnClickListener { presenter.onClickSingup() }
            textViewTerms.setOnClickListener { presenter.onClickTerms() }
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun goToHomePage() = HomeViewPagerActivity.start(requireContext())
    override fun goToSignUp() = SignUpActivity.start(requireContext())
    override fun goToWeb(url: String) = requireContext().openBrowser(url)
    override fun showError(error: String) = Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    override fun showErrorEmail(error: String) = etEmail.setError(error)
    override fun showErrorPassword(error: String) = etPassword.setError(error)
    override fun showErrorFormat(error: String) = etEmail.setError(error)
    override fun saveLoginSharedPreferences(id: Int?) {
        Log.i("userid", id.toString())
        val sharedPref: SharedPreferences? = activity?.getSharedPreferences(context?.getString(R.string.prefs_name), Context.MODE_PRIVATE)
        if (sharedPref != null) {
            sharedPref.edit().putBoolean(Extras.UserLogin.ISLOGIN, true).apply()
            sharedPref.edit().putInt(Extras.UserLogin.ID, id!!).apply()
        }
    }
    override fun showProgressDialog() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Cargando...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.show()
    }

    override fun dismissProgresddDialog() {
        progressDialog.dismiss()
    }
}