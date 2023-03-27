package com.example.jobseeker.utils

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobseeker.databinding.ItemDisplayNotesBinding
import com.example.jobseeker.room.Notes

class RecyclerViewAdapter: RecyclerView.Adapter<NoteViewHolder>() {
    var notesList:List<Notes>?=null
    var listener: OnItemClickListener? = null

    fun setList(list:List<Notes>?){
        notesList=list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {


        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDisplayNotesBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    fun setOnClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.titleText.text=notesList!![position].title.toString()
        holder.binding.subTitleText.text= notesList!![position].sub_title.toString()
        holder.binding.dataTimeText.text=notesList!![position].data_time.toString()
        if(notesList!![position].img_path!=null) {
            holder.binding.imageView.setImageBitmap(BitmapFactory.decodeFile(notesList!![position].img_path))
        }

        if(notesList!![position].color_info!=null) {
            holder.binding.cardView.setCardBackgroundColor(notesList!![position].color_info!!.toInt())
        }
        else {
            holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#171C26"))
        }
        //id=notesList!![position].id
        holder.binding.cardView.setOnClickListener {
            listener!!.onClicked(notesList!![position].id!!)
        }

    }


    override fun getItemCount(): Int {
        return notesList!!.size
    }

    interface OnItemClickListener{
        fun onClicked(noteId:Int)
    }


}

class NoteViewHolder(val binding: ItemDisplayNotesBinding):RecyclerView.ViewHolder(binding.root) {

}