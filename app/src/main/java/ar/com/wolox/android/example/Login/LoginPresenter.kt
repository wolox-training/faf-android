package ar.com.wolox.android.example.Login

import ar.com.wolox.wolmo.core.presenter.BasePresenter

class LoginPresenter constructor() : BasePresenter<LoginView>() {

    fun onClickLogin(fistName: String, lastName: String) {
        view?.dummyFunction()
    }

    fun onClickSingup() {
        view?.dummyFunction2()
    }

    fun onClickTerms() = view?.gotToWeb()
}