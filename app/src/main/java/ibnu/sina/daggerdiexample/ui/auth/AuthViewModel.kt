package ibnu.sina.daggerdiexample.ui.auth

import androidx.lifecycle.*
import ibnu.sina.daggerdiexample.SessionManager
import ibnu.sina.daggerdiexample.model.user.User
import ibnu.sina.daggerdiexample.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel(){

    fun authenticateWithId(id: Int){
        sessionManager.authenticatedWithId(queryUserId(id))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResources<User>> {
        return LiveDataReactiveStreams.fromPublisher<AuthResources<User>> {
            authApi.getFakeStuff(userId)
                .onErrorReturn {
                    val errUser = User(id = -1)
                    errUser
                }
                .map {userResult ->
                    if (userResult.id != -1) {
                         return@map AuthResources.error("Something went wrong", null) as AuthResources<User>
                    }

                    AuthResources.authenticated(userResult) as AuthResources<User>
                }
                .subscribeOn(Schedulers.io())
        }

        fun observeAuthUser(): LiveData<AuthResources<User>> = sessionManager.getAuthUser()
    }
}