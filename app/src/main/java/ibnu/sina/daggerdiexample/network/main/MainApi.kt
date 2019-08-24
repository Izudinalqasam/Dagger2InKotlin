package ibnu.sina.daggerdiexample.network.main

import ibnu.sina.daggerdiexample.model.post.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getPostFromUser(@Query("userId") id: Int?): Flowable<List<Post>>
}