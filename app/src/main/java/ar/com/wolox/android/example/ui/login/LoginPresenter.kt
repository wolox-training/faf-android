package ar.com.wolox.android.example.ui.login

import android.app.ProgressDialog
import android.content.Context
import android.widget.EditText
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.LoginRequest
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.PostRepository
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val postRepository: PostRepository) : CoroutineBasePresenter<LoginView>() {
    private lateinit var progressDialog: ProgressDialog
    fun onClickLogin(email: String, password: String, etEmail: EditText, etPassword: EditText, context: Context) = launch {

        if (email.isEmpty()) {
            etEmail.setError(context.getString(R.string.error_email))
        } else if (password.isEmpty()) {
            etPassword.setError(context.getString(R.string.error_password))
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
            etEmail.setError(context.getString(R.string.error_format))
        } else {
            val user = LoginRequest(email, password)
            showProgresDialog(context)
            networkRequest(postRepository.getLogin(user)) {
                onResponseSuccessful {
                    view?.goToHomePage()
                    progressDialog.dismiss()
                }
                onResponseFailed { _, _ ->
                    view?.showError(context.getString(R.string.error_values))
                    progressDialog.dismiss()
                }
                onCallFailure {
                    progressDialog.dismiss()
                    view?.showError(context.getString(R.string.error_conexion))
                }
            }
        }
    }

    fun onClickSingup() {
        view?.goToSignUp()
    }
    fun onClickTerms() = view?.goToWeb(WOLOX_URL)

    companion object {
        private const val WOLOX_URL = "www.wolox.com.ar"
    }
    fun showProgresDialog(context: Context) {
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Cargando...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.show()
    }
}