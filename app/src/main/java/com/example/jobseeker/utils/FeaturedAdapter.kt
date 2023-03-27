package com.example.jobseeker.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jobseeker.databinding.AdapterFeaturedJobBinding
import com.example.jobseeker.model.FeaturedJob

class FeaturedAdapter: RecyclerView.Adapter<FeaturedViewHolder>() {
    var featuredjobs = mutableListOf<FeaturedJob>()
    fun setFeaturedJobList(featuredjobs: List<FeaturedJob>) {
        this.featuredjobs = featuredjobs.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterFeaturedJobBinding.inflate(inflater, parent, false)
        return FeaturedViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        val featuredjob = featuredjobs[position]
        if (position < 5) {
            holder.binding.company.text = featuredjob.company
            holder.binding.title.text = featuredjob.title
            holder.binding.salary.text = featuredjob.salary
//            holder.binding.place.text = featuredjob.place
            holder.binding.time.text = featuredjob.category
            Glide.with(holder.itemView.context).load(featuredjob.image).into(holder.binding.image)
        } else {
            holder.itemView.visibility = View.GONE
        }
    }
    override fun getItemCount(): Int {
        return featuredjobs.size
    }
}
class FeaturedViewHolder(val binding: AdapterFeaturedJobBinding) : RecyclerView.ViewHolder(binding.root) {
}