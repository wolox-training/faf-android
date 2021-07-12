package ar.com.wolox.android.example.ui.login

import android.widget.EditText
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    fun onClickLogin(email: String, password: String, etEmail: EditText, etPassword: EditText) {

        if (email.isEmpty()) {
            etEmail.setError("Debe ingresar el email")
        } else if (password.isEmpty()) {
            etPassword.setError("Debe ingresar la contraseña")
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
            etEmail.setError("example@domain.com")
        } else { view?.goToHomePage() }
    }

    fun onClickSingup() {
        view?.goToSignUp()
    }
    fun onClickTerms() = view?.goToWeb(WOLOX_URL)

    companion object {
        private const val WOLOX_URL = "www.wolox.com.ar"
    }
}