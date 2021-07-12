package ar.com.wolox.android.example.ui.hompage

import android.content.Context
import android.content.SharedPreferences
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentHomePageBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class HomePageFragment private constructor() : WolmoFragment<FragmentHomePageBinding, HomePagePresenter>() {

    override fun layout() = R.layout.fragment_home_page
    override fun init() {
        val sharedPref: SharedPreferences? = activity?.getSharedPreferences(R.string.prefs_name.toString(), Context.MODE_PRIVATE)
        val nombre = sharedPref?.getString(R.string.login.toString(), "").toString()
        binding.tvUsuario.setText(nombre)
    }

    companion object {
        fun newInstance() = HomePageFragment()
    }
}