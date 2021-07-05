package ar.com.wolox.android.example.ui.login

import androidx.core.widget.addTextChangedListener
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class LoginFragment private constructor() : WolmoFragment<FragmentLoginBinding, LoginPresenter>() {

    override fun layout() = R.layout.fragment_login

    override fun init() {
    }

    override fun setListeners() {
        with(binding) {

            editTextEmail.addTextChangedListener {}
            editTextPassword.addTextChangedListener {}
            buttonLogin.setOnClickListener {

                // Validacion
                if (editTextEmail.text.toString().isEmpty()) {
                    editTextEmail.setError("Debe ingrese el correo")
                } else if (editTextPassword.text.toString().isEmpty()) {
                    editTextPassword.setError("Debe ingresar la contraseña")
                } else {
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text.toString()).matches()) {
                        editTextEmail.setError("El correo debe tener el formato: example@domain.com")
                    } else {
                        presenter.onClickLogin(editTextEmail.text.toString(), editTextPassword.text.toString())
                    }
                }
            }
            buttonSignup.setOnClickListener { presenter.onClickSingup() }
            textViewTerms.setOnClickListener { presenter.onClickTerms() }
        }
    }

    companion object {

    fun newInstance() = LoginFragment()
    }
}