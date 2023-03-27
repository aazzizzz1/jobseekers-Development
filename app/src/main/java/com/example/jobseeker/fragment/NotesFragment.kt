package com.example.jobseeker.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentCreateNoteBinding
import com.example.jobseeker.databinding.FragmentNotesBinding
import com.example.jobseeker.room.NoteDatabase
import com.example.jobseeker.room.Notes
import com.example.jobseeker.utils.RecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private var param1: String? = null
    private var param2: String? = null

    var noteId=-1
    var list:List<Notes>?=null
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private var myAdapter= RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager=
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var job= CoroutineScope(Dispatchers.Main).launch {
            var notesList= NoteDatabase.getNotesDatabase(requireContext()).getNotesDao().getAllNote()
            list=notesList
            myAdapter.setList(notesList)
            binding.recyclerView.adapter=myAdapter

            Log.i("Arjun","Showing recycler view")
            Log.i("Arjun",notesList.toString())
        }
        myAdapter.notifyDataSetChanged()

        myAdapter!!.setOnClickListener(onClicked)
        myAdapter.setHasStableIds(true)
        // binding.recyclerView.adapter!!.notifyDataSetChanged()

        binding.floatingActionButton.setOnClickListener {
            val bundle = bundleOf("noteId" to -1)
            findNavController().navigate(R.id.action_notesFragment_to_createNoteFragment, bundle)
        }

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            broadcastReceiver, IntentFilter("Notify recyclerView")
        )

        // Inflate the layout for this fragment
        return binding.root

    }

    val broadcastReceiver: BroadcastReceiver =object: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {

            Log.i("Arjun","Recycle")
            binding.recyclerView.adapter!!.notifyDataSetChanged()

        }
    }

    private val onClicked = object :RecyclerViewAdapter.OnItemClickListener{
        override fun onClicked(noteId: Int) {
            val bundle = bundleOf("noteId" to noteId)
            findNavController().navigate(R.id.action_notesFragment_to_createNoteFragment,bundle)
        }

    }
}