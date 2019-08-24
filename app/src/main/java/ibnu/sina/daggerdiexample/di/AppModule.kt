package ibnu.sina.daggerdiexample.di

import android.app.Application
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import ibnu.sina.daggerdiexample.R
import ibnu.sina.daggerdiexample.model.user.User
import ibnu.sina.daggerdiexample.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideOkhttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @JvmStatic
    @Singleton
    @Provides
    fun provideRequestOption() = RequestOptions.
            placeholderOf(R.mipmap.ic_launcher).
            error(R.mipmap.ic_launcher)

    @JvmStatic
    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions) =
            Glide.with(application).applyDefaultRequestOptions(requestOptions)

//    @JvmStatic
//    @Nullable
//    @Provides
//    fun provideMipmap(application: Application) =
//            ContextCompat.getDrawable(application, R.mipmap.ic_launcher)

    @JvmStatic
    @Singleton
    @Provides
    @Named("app_user")
    fun provideUser() = User()

}