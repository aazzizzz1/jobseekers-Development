package com.example.jobseeker.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jobseeker.MainActivity
import com.example.jobseeker.databinding.FragmentProfileBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null){
            binding.text2ProfileEmail.setText(user.email)
        }

        binding.btnProfileLogout.setOnClickListener {
            logout()
        }

        binding.buttonSaveChanges.setOnClickListener {
            saveChanges()
        }

    }

    private fun saveChanges() {
        val newDisplayName = binding.textViewChangeEmail.text.toString().trim()
        val newPassword = binding.newPasswordEditText.text.toString().trim()
        val newEmail = binding.newEmailEditText.text.toString().trim()

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (newDisplayName.isNotEmpty()) {
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(newDisplayName)
                .build()

            user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireActivity(), " $newEmail $newPassword berhasil update", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireActivity(), " Anda gagal update", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        val currentPassword = binding.textViewChangePassword.text.toString().trim()

        if (newEmail.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
            binding.newEmailEditText.error = "Invalid email address"
            binding.newEmailEditText.requestFocus()
            return
        }

        if (currentPassword.isNotEmpty() && newPassword.isNotEmpty()) {
            val credential = EmailAuthProvider.getCredential(user!!.email!!, currentPassword)
            user.reauthenticate(credential).addOnCompleteListener { reAuthTask ->
                if (newPassword.length < 6 && newPassword.isNotEmpty()){
                    binding.newPasswordEditText.error = "Password harus ebih dari 6 karakter"
                    binding.newPasswordEditText.requestFocus()
                }
                if (reAuthTask.isSuccessful) {
                    user.updatePassword(newPassword).addOnCompleteListener { passwordTask ->
                        if (passwordTask.isSuccessful) {
                            Toast.makeText(requireActivity(), " $newPassword update", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireActivity(), " $newPassword gagal update", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(requireActivity(), " $newPassword gagal update", Toast.LENGTH_SHORT).show()
                }
            }
        }

        if (newEmail.isNotEmpty()) {
            user!!.updateEmail(newEmail).addOnCompleteListener { emailTask ->
                if (emailTask.isSuccessful) {
                    Toast.makeText(requireActivity(), " $newEmail update", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), " $newEmail gagal update", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun logout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent (activity, MainActivity::class.java)
        activity?.startActivity(intent)
        activity?.finish()

    }

}