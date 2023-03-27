package com.example.jobseeker.room

class JobRepository(private val jobDao: JobDao) {

    suspend fun insertJob(job: JobEntity) {
        jobDao.insertJob(job)
    }

    suspend fun getAllJobs(): List<JobEntity> {
        return jobDao.getAllJobs()
    }

    suspend fun getJobById(id: Int): JobEntity? {
        return jobDao.getJobById(id)
    }

    suspend fun updateJob(job: JobEntity) {
        jobDao.updateJob(job)
    }

    suspend fun deleteJob(job: JobEntity) {
        jobDao.deleteJob(job)
    }
}

//panduan Dengan menggunakan kode di atas, kita dapat melakukan operasi CRUD pada tabel "jobs" dalam database room dengan mudah. Misalnya, untuk menambahkan data baru, kita dapat menggunakan kode berikut:
//val job = JobEntity(
//    0,
//    "Contoh Judul",
//    "Contoh Kategori",
//    "Contoh Deskripsi",
//    "Contoh Image"
//)
//jobRepository.insertJob(job)

//Untuk mengambil semua data dari tabel, kita dapat menggunakan kode berikut:
//val allJobs = jobRepository.getAllJobs()
//
//Untuk mengubah data yang sudah ada, kita dapat menggunakan kode berikut:
//val jobToUpdate = jobRepository.getJobById(1)
//jobToUpdate?.kategori = "Kategori Baru"
//jobRepository.updateJob(jobToUpdate!!)
//
//Untuk menghapus data dari tabel, kita dapat menggunakan kode berikut:
//val jobToDelete = jobRepository.getJobById(1)
//jobToDelete?.let { jobRepository.deleteJob(it) }


