package ar.com.wolox.android.example.ui.viewpager.detailnews

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailModule {
    @ContributesAndroidInjector
    internal abstract fun DetailActivity(): DetailActivity

    @ContributesAndroidInjector
    internal abstract fun DetailFragment(): DetailFragment
}