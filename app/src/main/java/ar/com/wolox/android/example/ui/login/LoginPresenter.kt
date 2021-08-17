package ar.com.wolox.android.example.ui.login

import android.util.Log
import ar.com.wolox.android.example.model.LoginRequest
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.PostRepository
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val postRepository: PostRepository) : CoroutineBasePresenter<LoginView>() {
    fun onClickLogin(email: String, password: String) = launch {

        if (email.isEmpty()) {
            view?.showErrorEmail("Ingrese El Correo")
        } else if (password.isEmpty()) {
            view?.showErrorPassword("Ingrese La Contraseña")
        } else {
            if (!androidx.core.util.PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
                view?.showErrorFormat("El email debe tener el formato example@example.com")
            } else {
                view?.showProgressDialog()
                val user = LoginRequest(email, password)
                networkRequest(postRepository.getLogin(user)) {
                    onResponseSuccessful { response ->
                        Log.i("resposse", response?.data?.name.toString())
                        view?.saveLoginSharedPreferences(response?.data?.id)
                        view?.goToHomePage()
                        view?.dismissProgresddDialog()
                    }
                    onResponseFailed { _, _ ->
                        view?.showError("Correo o Contraseña Invalidos")
                        view?.dismissProgresddDialog()
                    }
                    onCallFailure {
                        view?.showError("Error En La Conexion")
                        view?.dismissProgresddDialog()
                    }
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
}