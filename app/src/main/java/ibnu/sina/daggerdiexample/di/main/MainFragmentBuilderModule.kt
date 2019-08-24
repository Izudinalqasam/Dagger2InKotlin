package ibnu.sina.daggerdiexample.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ibnu.sina.daggerdiexample.ui.main.post.PostFragment
import ibnu.sina.daggerdiexample.ui.main.profile.ProfileFragment

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributorProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributorPostFragment(): PostFragment
}