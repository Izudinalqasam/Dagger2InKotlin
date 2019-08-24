package ibnu.sina.daggerdiexample.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ibnu.sina.daggerdiexample.viewmodel.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}