package com.example.jobseeker.remote

import com.example.jobseeker.model.FeaturedJob
import com.example.jobseeker.model.Job
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://restapi-73392-default-rtdb.asia-southeast1.firebasedatabase.app/furniture/-NPf7_HLegePq8e24XXU.json
//https://restapi-73392-default-rtdb.asia-southeast1.firebasedatabase.app/furniture/-NPw7r0Gw1tdBRlKhDEz.json
interface RetrofitService {
    @GET("-NPf7_HLegePq8e24XXU.json")
    fun getAllJobs(): Call<List<Job>>
//    fun getData(@Query("limit") limit: Int = 5): Call<List<Data>>

    @GET("-NPw7r0Gw1tdBRlKhDEz.json")
    fun getAllFeaturedJobs(
    ): Call<List<FeaturedJob>>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://restapi-73392-default-rtdb.asia-southeast1.firebasedatabase.app/furniture/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}