package ar.com.wolox.android.example.ui.viewpager.homepageviewpager

import ar.com.wolox.android.example.ui.hompage.HomePageFragment
import ar.com.wolox.android.example.ui.viewpager.news.NewsFragment
import ar.com.wolox.android.example.ui.viewpager.profile.ProfileFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
@Module(subcomponents = [(HomePageViewPagerActivitySubcomponet::class)])
abstract class HomeViewPagerActivityModule {

    @Binds
    @IntoMap
    @ClassKey(HomePageViewPagerActivity::class)
    internal abstract fun bindHomePageViewPagerActivityFactory(
        builder: HomePageViewPagerActivitySubcomponet.Builder
    ): AndroidInjector.Factory<*>

    @ContributesAndroidInjector
    internal abstract fun homePageFragment(): HomePageFragment

    @ContributesAndroidInjector
    internal abstract fun newsFragments(): NewsFragment

    @ContributesAndroidInjector
    internal abstract fun profileFragment(): ProfileFragment
}