package ibnu.sina.daggerdiexample.di.main

import dagger.Module
import dagger.Provides
import ibnu.sina.daggerdiexample.network.main.MainApi
import ibnu.sina.daggerdiexample.ui.main.post.PostRecyclerAdapter
import retrofit2.Retrofit

@Module
object MainModule {

    @JvmStatic
    @MainScope
    @Provides
    fun provideAdapter() = PostRecyclerAdapter()

    @JvmStatic
    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit) = retrofit.create(MainApi::class.java)
}