package ar.com.wolox.android.example.ui.login

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    fun onClickLogin(fistName: String, lastName: String) {
        view?.dummyFunction()
    }

    fun onClickSingup() {
        view?.dummyFunctionTwo()
    }

    fun onClickTerms() = view?.goToWeb()

    fun onClickDummy() = view?.dummyFunctionFour()
    fun onClickDummy2() = view?.dummyFunctionFive()
}