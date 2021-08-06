package ar.com.wolox.android.example

import android.content.Context
import android.content.SharedPreferences
import ar.com.wolox.android.BuildConfig
import ar.com.wolox.android.R
import ar.com.wolox.android.example.di.DaggerAppComponent
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.WolmoApplication
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager
import ar.com.wolox.wolmo.networking.di.DaggerNetworkingComponent
import ar.com.wolox.wolmo.networking.di.NetworkingComponent
import com.google.gson.FieldNamingPolicy
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import javax.inject.Inject

class BootstrapApplication : WolmoApplication() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharePref: SharedPreferencesManager
    lateinit var userSession: UserSession
    override fun onInit() {
        // Initialize Application stuff here
        sharedPreferences = getSharedPreferences(getString(R.string.prefs_name), Context.MODE_PRIVATE)
        sharePref = SharedPreferencesManager(sharedPreferences)
        userSession = UserSession(sharePref)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    override fun applicationInjector(): AndroidInjector<BootstrapApplication> {
        return DaggerAppComponent.builder().networkingComponent(buildDaggerNetworkingComponent())
                .sharedPreferencesName(BaseConfiguration.SHARED_PREFERENCES_NAME).application(this)
                .create(this)
    }

    private fun buildDaggerNetworkingComponent(): NetworkingComponent {
        val builder = DaggerNetworkingComponent.builder().baseUrl(
                BaseConfiguration.EXAMPLE_CONFIGURATION_KEY)
                .gsonNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        if (BuildConfig.DEBUG) {
            builder.okHttpInterceptors(buildHttpLoggingInterceptor(Level.BODY), headersInterceptor())
        }
        return builder.build()
    }

    /**
     * Returns a [HttpLoggingInterceptor] with the newLevel given by **newLevel**.
     *
     * @param newLevel - Logging newLevel for the interceptor.
     * @return New instance of interceptor
     */
    private fun buildHttpLoggingInterceptor(newLevel: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = newLevel
        }
    }
    private fun headersInterceptor() = Interceptor { chain ->
        with(BaseConfiguration) {
            val request = chain.request().let {
                if (userSession.islogin!!) {
                    it.newBuilder()
                        .addHeader(HEADER_ACCESS_TOKEN, userSession.accessToken!!)
                        .addHeader(HEADER_CLIENT, userSession.client!!)
                        .addHeader(HEADER_UID, userSession.uid!!)
                        .build()
                } else {
                    it
                }
            }
            val response = chain.proceed(request)
            if (!response.header(HEADER_ACCESS_TOKEN).isNullOrEmpty()) {
                userSession.accessToken = response.header(HEADER_ACCESS_TOKEN)
                userSession.islogin = true
            }
            if (!response.header(HEADER_CLIENT).isNullOrEmpty()) {
                userSession.client = response.header(HEADER_CLIENT)
            }
            if (!response.header(HEADER_UID).isNullOrEmpty()) {
                userSession.uid = response.header(HEADER_UID)
            }
            response
        }
    }
}
