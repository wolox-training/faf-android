package ar.com.wolox.android.example.ui.viewpager.homepageviewpager

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface HomePageViewPagerActivitySubcomponet : AndroidInjector<HomePageViewPagerActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomePageViewPagerActivity>()
}