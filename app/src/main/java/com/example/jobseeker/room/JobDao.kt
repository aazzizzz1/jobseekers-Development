package com.example.jobseeker.room

import androidx.room.*

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobEntity)

    @Query("SELECT * FROM jobs")
    suspend fun getAllJobs(): List<JobEntity>

    @Query("SELECT * FROM jobs WHERE id = :id")
    suspend fun getJobById(id: Int): JobEntity?

    @Update
    suspend fun updateJob(job: JobEntity)

    @Delete
    suspend fun deleteJob(job: JobEntity)
}
