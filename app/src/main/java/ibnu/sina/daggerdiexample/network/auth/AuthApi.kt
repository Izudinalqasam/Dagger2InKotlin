package ibnu.sina.daggerdiexample.network.auth

import ibnu.sina.daggerdiexample.model.user.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getFakeStuff(@Path("id") id: Int): Flowable<User>
}