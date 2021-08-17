package ar.com.wolox.android.example.utils

import android.app.Activity
import android.content.SharedPreferences
import androidx.fragment.app.Fragment

/**
 * Util class to store keys to use with [SharedPreferences] or as argument between
 * [Fragment] or [Activity].
 */
object Extras {

    object UserLogin {
        const val USERNAME = "username"
        const val TOKEN = "token"
        const val CLIENT = "client"
        const val UID = "uid"
        const val ISLOGIN = "islogin"
    }

    object ViewPager {
        const val FAVOURITE_COLOR_KEY = "FAVOURITE_COLOR_KEY"
    }
}
