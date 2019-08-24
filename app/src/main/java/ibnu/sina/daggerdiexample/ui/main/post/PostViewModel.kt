package ibnu.sina.daggerdiexample.ui.main.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import ibnu.sina.daggerdiexample.SessionManager
import ibnu.sina.daggerdiexample.model.post.Post
import ibnu.sina.daggerdiexample.model.user.User
import ibnu.sina.daggerdiexample.network.main.MainApi
import ibnu.sina.daggerdiexample.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel(){


    private val posts = MediatorLiveData<Resource<out List<Post>>>()

    fun observePosts(): LiveData<Resource<out List<Post>>> {
        posts.value = Resource.loading(null)

        val source = LiveDataReactiveStreams.fromPublisher<Resource<List<Post>>> {
            mainApi.getPostFromUser(sessionManager.getAuthUser().value?.data?.id)
                .onErrorReturn {
                    val post = Post(2, -1, "", "")
                    val arrayPost = mutableListOf<Post>()
                    arrayPost.add(post)

                    arrayPost
                }
                .map {posts ->
                    if (posts.isNotEmpty()){
                        if (posts[0].id == -1){
                            return@map Resource.error("Something went wrong", null) as Resource<List<Post>>
                        }else{
                            return@map Resource.authenticated(posts) as Resource<List<Post>>
                        }
                    }else {
                        return@map Resource.error("Something went wrong", null) as Resource<List<Post>>
                    }

                }
                .subscribeOn(Schedulers.io())
        }

        posts.addSource(source){
            posts.value = it
            posts.removeSource(source)
        }

        return posts
    }


}