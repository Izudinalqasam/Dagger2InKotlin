package ibnu.sina.daggerdiexample.ui.main.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment

import ibnu.sina.daggerdiexample.R
import ibnu.sina.daggerdiexample.ui.auth.AuthResources
import ibnu.sina.daggerdiexample.viewmodel.ViewModelFactory
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    @Inject lateinit var providerFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, providerFactory)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.getAuthenticaredUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticaredUser().observe(viewLifecycleOwner, Observer { userAuthRes ->
            userAuthRes?.let {
                when(userAuthRes.status){
                    AuthResources.Status.SUCCESS -> {}
                    AuthResources.Status.LOADING -> {}
                }
            }
        })
    }


}
