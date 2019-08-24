package ibnu.sina.daggerdiexample.di.auth

import dagger.Module
import dagger.Provides
import ibnu.sina.daggerdiexample.model.user.User
import ibnu.sina.daggerdiexample.network.auth.AuthApi
import retrofit2.Retrofit
import javax.inject.Named

@Module
object AuthModule {

    @JvmStatic
    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit) =
            retrofit.create(AuthApi::class.java)

    @JvmStatic
    @AuthScope
    @Provides
    @Named("auth_user")
    fun provideUser() = User()
}