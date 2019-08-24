package ibnu.sina.daggerdiexample.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ibnu.sina.daggerdiexample.SessionManager
import ibnu.sina.daggerdiexample.model.user.User
import ibnu.sina.daggerdiexample.ui.auth.AuthResources
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel(){

    fun getAuthenticaredUser(): LiveData<AuthResources<User>> = sessionManager.getAuthUser()
}