package ibnu.sina.daggerdiexample.ui.main.post


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment

import ibnu.sina.daggerdiexample.R
import ibnu.sina.daggerdiexample.ui.main.Resource
import ibnu.sina.daggerdiexample.util.VerticalSpaceItemDecoration
import ibnu.sina.daggerdiexample.viewmodel.ViewModelFactory
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    @Inject lateinit var providerFactory: ViewModelFactory
    @Inject lateinit var adapter: PostRecyclerAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this, providerFactory)[PostViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       initRecyclerView()
        subscribeObserver()
    }

    private fun subscribeObserver(){
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(this, Observer { listRes ->
            listRes?.let {
                when(it.status){
                    Resource.Status.LOADING -> {}
                    Resource.Status.SUCCESS -> {}
                }
            }
        })
    }

    private fun initRecyclerView(){
        val decoration = VerticalSpaceItemDecoration(15)
    }

}
