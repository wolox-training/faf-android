package ar.com.wolox.android.example.ui.hompage

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomePageModule {

    @ContributesAndroidInjector
    internal abstract fun homePageActivity(): HomePageActivity

    @ContributesAndroidInjector
    internal abstract fun homePageFragment(): HomePageFragment
}