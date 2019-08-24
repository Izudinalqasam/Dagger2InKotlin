package ibnu.sina.daggerdiexample

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import ibnu.sina.daggerdiexample.ui.auth.AuthActivity
import ibnu.sina.daggerdiexample.ui.auth.AuthResources
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        subscribeObserver()
    }

    private fun subscribeObserver(){
        sessionManager.getAuthUser().observe(this, Observer { userAuthRes ->
            userAuthRes?.let {
                when(userAuthRes.status){

                    AuthResources.Status.LOADING -> {}
                    AuthResources.Status.ERROR -> {}
                    AuthResources.Status.SUCCESS -> {}
                    AuthResources.Status.LOGOUT -> navLoginScreen()
                }
            }
        })
    }

    private fun navLoginScreen(){
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}