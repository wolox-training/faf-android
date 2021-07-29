package ar.com.wolox.android.example.ui.login

interface LoginView {

    fun goToHomePage()
    fun goToSignUp()
    fun goToWeb(url: String)
    fun showError(error: String)
    fun showErrorEmail(error: String)
    fun showErrorPassword(error: String)
    fun showErrorFormat(error: String)
    fun saveLoginSharedPreferences()
    fun showProgressDialog()
    fun dismissProgresddDialog()
}