package ibnu.sina.daggerdiexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ibnu.sina.daggerdiexample.model.user.User
import ibnu.sina.daggerdiexample.ui.auth.AuthResources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val cachedUser = MediatorLiveData<AuthResources<User>>()

    fun authenticatedWithId(source: LiveData<AuthResources<User>>) {
        cachedUser?.let {
            cachedUser.value = AuthResources.loading(null)
            cachedUser.addSource(source) {
                cachedUser.value = it
                cachedUser.removeSource(source)
            }
        }
    }

    fun logOut() {
        cachedUser.value = AuthResources.logout()
    }

    fun getAuthUser(): LiveData<AuthResources<User>> = cachedUser

}