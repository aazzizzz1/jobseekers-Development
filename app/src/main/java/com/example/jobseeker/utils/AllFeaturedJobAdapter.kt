package com.example.jobseeker.utils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jobseeker.databinding.AdapterFeaturedJobBinding
import com.example.jobseeker.model.FeaturedJob

class AllFeaturedJobAdapter: RecyclerView.Adapter<AllFeaturedJobAdapterViewHolder>() {
    var featuredjobs = mutableListOf<FeaturedJob>()
    fun setFeaturedJobList(featuredjobs: List<FeaturedJob>) {
        this.featuredjobs = featuredjobs.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllFeaturedJobAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterFeaturedJobBinding.inflate(inflater, parent, false)
        return AllFeaturedJobAdapterViewHolder(binding)
    }
    override fun onBindViewHolder(holder: AllFeaturedJobAdapterViewHolder, position: Int) {
        val featuredjob = featuredjobs[position]
        holder.binding.company.text = featuredjob.company
        holder.binding.title.text = featuredjob.title
        holder.binding.salary.text = featuredjob.salary
//        holder.binding.place.text = featuredjob.place
        holder.binding.time.text = featuredjob.category
        Glide.with(holder.itemView.context).load(featuredjob.image).into(holder.binding.image)

    }
    override fun getItemCount(): Int {
        return featuredjobs.size
    }
}
class AllFeaturedJobAdapterViewHolder(val binding: AdapterFeaturedJobBinding) : RecyclerView.ViewHolder(binding.root) {
}