package ar.com.wolox.android.example.ui.viewpager.homeviewpager

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface HomeViewPagerActivitySubcomponent : AndroidInjector<HomeViewPagerActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeViewPagerActivity>()
}