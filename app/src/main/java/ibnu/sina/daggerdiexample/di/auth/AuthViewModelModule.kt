package ibnu.sina.daggerdiexample.di.auth

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ibnu.sina.daggerdiexample.di.main.ViewModelKey
import ibnu.sina.daggerdiexample.ui.auth.AuthViewModel

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}