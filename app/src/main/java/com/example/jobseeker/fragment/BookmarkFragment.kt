package com.example.jobseeker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentBookmarkBinding
import com.example.jobseeker.databinding.FragmentLoginBinding

class BookmarkFragment : Fragment(R.layout.fragment_bookmark) {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        binding.btnJob.setOnClickListener {
            findNavController().navigate(R.id.action_id_bookmark_fragment_to_tambahJobFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
