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
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentHomeBinding
import com.example.jobseeker.model.MainViewModel
import com.example.jobseeker.model.MyViewModelFactory
import com.example.jobseeker.remote.MainRepository
import com.example.jobseeker.remote.RetrofitService
import com.example.jobseeker.utils.FeaturedAdapter
import com.example.jobseeker.utils.MainAdapter

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    private lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter()
    private val featuredadapter = FeaturedAdapter()
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View {
    // Inflate the layout using DataBindingUtil
    val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

    // Initialize the viewModel property
    viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
        MainViewModel::class.java
    )

    // Set up the RecyclerView adapters and observe the LiveData objects
    binding.recyclerview.adapter = adapter
    binding.recyclerviewfeaturedjob.adapter = featuredadapter

    getjobs()
    getfeaturedjobs()

    binding.allFeaturedjobs.setOnClickListener {
        findNavController().navigate(R.id.action_id_home_fragment_to_id_search_fragment)
    }
    binding.allJobs.setOnClickListener {
        findNavController().navigate(R.id.action_id_home_fragment_to_id_search_fragment)
    }
    // Return the root view from the binding object
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

    private fun getjobs() {
        viewModel.jobList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setJobList(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
        })
        viewModel.getAllJobs()
    }
}

