package ibnu.sina.daggerdiexample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ibnu.sina.daggerdiexample.di.auth.AuthModule
import ibnu.sina.daggerdiexample.di.auth.AuthScope
import ibnu.sina.daggerdiexample.di.auth.AuthViewModelModule
import ibnu.sina.daggerdiexample.di.main.MainFragmentBuilderModule
import ibnu.sina.daggerdiexample.di.main.MainModule
import ibnu.sina.daggerdiexample.di.main.MainScope
import ibnu.sina.daggerdiexample.di.main.MainViewModelModule
import ibnu.sina.daggerdiexample.ui.auth.AuthActivity
import ibnu.sina.daggerdiexample.ui.main.MainActivity

@Module
abstract class ActivityBuilderModule {

    // to make the module only be accessed by auth activity not whole application
    @AuthScope
    @ContributesAndroidInjector(
        modules = [
            AuthViewModelModule::class,
            AuthModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuilderModule::class,
            MainViewModelModule::class,
            MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

}