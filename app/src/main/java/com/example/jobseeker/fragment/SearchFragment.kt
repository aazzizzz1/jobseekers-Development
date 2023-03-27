package com.example.jobseeker.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentSearchBinding
import com.example.jobseeker.model.MainViewModel
import com.example.jobseeker.model.MyViewModelFactory
import com.example.jobseeker.remote.MainRepository
import com.example.jobseeker.remote.RetrofitService
import com.example.jobseeker.utils.AllFeaturedJobAdapter

class SearchFragment : Fragment() {

    private val TAG = "HomeFragment"

    private lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    private val featuredadapter = AllFeaturedJobAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using DataBindingUtil
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        // Initialize the viewModel property
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java
        )

        // Set up the RecyclerView adapters and observe the LiveData objects
        binding.recyclerviewfeaturedjob.adapter = featuredadapter

        getfeaturedjobs()

        return binding.root

    }

    private fun getfeaturedjobs() {

        viewModel.featuredjobList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            featuredadapter.setFeaturedJobList(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
        })
        viewModel.getAllFeaturedJobs()
    }
}
