package com.example.jobseeker.remote

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllJobs() = retrofitService.getAllJobs()

    fun getAllFeaturedJobs() = retrofitService.getAllFeaturedJobs()

}