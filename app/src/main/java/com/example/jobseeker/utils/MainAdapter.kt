package com.example.jobseeker.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jobseeker.databinding.AdapterJobBinding
import com.example.jobseeker.model.Job

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {
    var jobs = mutableListOf<Job>()
    fun setJobList(jobs: List<Job>) {
        this.jobs = jobs.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterJobBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val job = jobs[position]
        holder.binding.title.text = job.title
        holder.binding.salaryTextView.text = job.salary
        holder.binding.descriptionTextView.text = job.description
        holder.binding.fullTimeTextView.text = job.category
        Glide.with(holder.itemView.context).load(job.image_url).into(holder.binding.imageview)
    }
    override fun getItemCount(): Int {
        return jobs.size
    }
}
class MainViewHolder(val binding: AdapterJobBinding) : RecyclerView.ViewHolder(binding.root) {}