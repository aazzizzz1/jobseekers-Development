package com.example.jobseeker.model

data class Job(
    val category: String,
    val company: String,
    val company_city: String,
    val created_at: String,
    val description: String,
    val id: Int,
    val image_url: String,
    val release_year: Int,
    val salary: String,
    val title: String,
    val updated_at: String
)

data class FeaturedJob(

    val category: String,
    val company: String,
    val description: String,
    val id: Int,
    val image: String,
    val place: String,
    val salary: String,
    val title: String
)
