package com.example.jobseeker.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val judul: String,
    val kategori: String,
    val deskripsi: String,
    val image: String
)

