package ibnu.sina.daggerdiexample.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import ibnu.sina.daggerdiexample.R
import ibnu.sina.daggerdiexample.model.user.User
import ibnu.sina.daggerdiexample.ui.main.MainActivity
import ibnu.sina.daggerdiexample.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity() {

//    @Inject lateinit var logo: Drawable
    @Inject lateinit var  requestManager: RequestManager
    @Inject lateinit var providerFactory: ViewModelFactory


    @Inject @Named("app_user") lateinit var appUser: User
    @Inject @Named("auth_user") lateinit var authUser: User

    private val viewmodel by lazy {
        ViewModelProviders.of(this, providerFactory)[AuthViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
//
//    private fun setLogo(){
//        requestManager
//            .load(logo)
//            .into(imgGlide)
//    }

    private fun observeUser(){
        viewmodel.observeAuthUser().observe(this, Observer { userAuthRes ->
            userAuthRes?.let {
                when(userAuthRes.status){

                    AuthResources.Status.LOADING -> {}
                    AuthResources.Status.SUCCESS -> {}
                    AuthResources.Status.ERROR -> {}

                }
            }
        })
    }

    private fun onLoginSuccess(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun attemptLogin(){
       viewmodel.authenticateWithId(1)
    }
}
