package com.example.jobseeker.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobseeker.remote.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val jobList = MutableLiveData<List<Job>>()
    val featuredjobList = MutableLiveData<List<FeaturedJob>>()
    val searchjob : LiveData<List<FeaturedJob>> = featuredjobList
    val errorMessage = MutableLiveData<String>()

    fun getAllJobs() {
        val response = repository.getAllJobs()
        response.enqueue(object : Callback<List<Job>> {
            override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
                jobList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Job>>, t: Throwable) {
                errorMessage.postValue(t.message)
                "Data tidak bisa ditampilkan"
            }
        })
    }

    fun getAllFeaturedJobs() {
        val response = repository.getAllFeaturedJobs()
        response.enqueue(object : Callback<List<FeaturedJob>> {
            override fun onResponse(call: Call<List<FeaturedJob>>, response: Response<List<FeaturedJob>>) {
                featuredjobList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<FeaturedJob>>, t: Throwable) {
                errorMessage.postValue(t.message)
                "Data tidak bisa ditampilkan"
            }
        })
    }

}