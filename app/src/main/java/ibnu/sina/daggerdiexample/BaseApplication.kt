package ibnu.sina.daggerdiexample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ibnu.sina.daggerdiexample.di.AppComponent
import ibnu.sina.daggerdiexample.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return AppComponent.builder()
            .application(this)
            .build()
    }
}