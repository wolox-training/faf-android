package ar.com.wolox.android.example.ui.login

interface LoginView {

    fun goToHomePage()
    fun goToSignUp()
    fun goToWeb(url: String)
}