package ar.com.wolox.android.example.ui.login

import ar.com.wolox.wolmo.core.presenter.BasePresenter

class LoginPresenter constructor() : BasePresenter<LoginView>() {

    fun onClickLogin(fistName: String, lastName: String) {
        view?.dummyFunction()
    }

    fun onClickSingup() {
        view?.dummyFunction2()
    }

    fun onClickTerms() = view?.gotToWeb()

    fun onClickDummy()= view?.dummyFunction4()
}