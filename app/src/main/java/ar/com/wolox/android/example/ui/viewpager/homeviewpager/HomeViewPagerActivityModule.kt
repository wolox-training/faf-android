package ar.com.wolox.android.example.ui.viewpager.homeviewpager

import ar.com.wolox.android.example.ui.hompage.HomePageFragment
import ar.com.wolox.android.example.ui.viewpager.news.NewsFragment
import ar.com.wolox.android.example.ui.viewpager.profile.ProfileFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [(HomeViewPagerActivitySubcomponent::class)])
abstract class HomeViewPagerActivityModule {

    @Binds
    @IntoMap
    @ClassKey(HomeViewPagerActivity::class)
    internal abstract fun binHomeViewPagerActivityFactory(
        builder: HomeViewPagerActivitySubcomponent.Builder
    ): AndroidInjector.Factory<*>

    @ContributesAndroidInjector
    internal abstract fun homePageFragmet(): HomePageFragment

    @ContributesAndroidInjector
    internal abstract fun newsFragment(): NewsFragment

    @ContributesAndroidInjector
    internal abstract fun profileFragmetn(): ProfileFragment
}