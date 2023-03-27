package com.example.jobseeker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jobseeker.databinding.FragmentTambahJobBinding
import com.example.jobseeker.room.JobDatabase
import com.example.jobseeker.room.JobEntity
import com.example.jobseeker.room.JobRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TambahJobFragment : Fragment() {

    private lateinit var binding: FragmentTambahJobBinding
    private lateinit var jobRepository: JobRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTambahJobBinding.inflate(inflater, container, false)
        jobRepository = JobRepository(JobDatabase.getDatabase(requireContext()).jobDao())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add a button to fetch all jobs
        binding.btnGetAllJobs.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val jobs = jobRepository.getAllJobs()
                withContext(Dispatchers.Main) {
                    // Display the jobs in a Toast message
                    Toast.makeText(requireContext(), jobs.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnSimpan.setOnClickListener {
            val judul = binding.etJudul.text.toString()
            val kategori = binding.etKategori.text.toString()
            val deskripsi = binding.etDeskripsi.text.toString()
            val image = binding.etImage.text.toString()

            val job = JobEntity(
                0,
                judul,
                kategori,
                deskripsi,
                image
            )
            GlobalScope.launch(Dispatchers.IO) {
                jobRepository.insertJob(job)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                    // tambahkan kode untuk kembali ke halaman sebelumnya
                }
            }
        }
    }
}