package ar.com.wolox.android.example.utils

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager

import javax.inject.Inject

@ApplicationScope
class UserSession @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    // Really, we don't need to query the username because this instance live as long as the
    // application, but we should add a check in case Android decides to kill the application
    // and return to a state where this isn't initialized.
    var username: String? = null
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.USERNAME, null].also {
            field = it
        }
        set(username) {
            field = username
            sharedPreferencesManager.store(Extras.UserLogin.USERNAME, username)
        }
    var accessToken: String? = null
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.TOKEN, null].also {
            field = it
        }
        set(token) {
            field = token
            sharedPreferencesManager.store(Extras.UserLogin.TOKEN, token)
        }
    var client: String? = null
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.CLIENT, null].also {
            field = it
        }
        set(client) {
            field = client
            sharedPreferencesManager.store(Extras.UserLogin.CLIENT, client)
        }
    var uid: String? = null
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.UID, null].also {
            field = it
        }
        set(uid) {
            field = uid
            sharedPreferencesManager.store(Extras.UserLogin.UID, uid)
        }
    var islogin: Boolean? = null
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.ISLOGIN, false].also {
            field = it
        }
        set(value) {
            field = value
            value?.let { sharedPreferencesManager.store(Extras.UserLogin.ISLOGIN, it) }
        }
}
