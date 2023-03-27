package com.example.jobseeker.fragment

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {

            var email = binding.rEmail.text.toString()
            var password = binding.rPassword.text.toString()

            if (email.isEmpty()){
                binding.rEmail.error = "email harus diisi"
                binding.rEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.rEmail.error = "email tidak valid"
                binding.rEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.rPassword.error = "Password harus diisi"
                binding.rPassword.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6){
                binding.rPassword.error = "Password harus 6 karakter atau lebih"
                binding.rPassword.requestFocus()
                return@setOnClickListener
            }

            dataUser(email, password)

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.textRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return binding.root
    }

    private fun dataUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) {
            if (it.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(requireActivity(), "Berhasil membuat akun", Toast.LENGTH_SHORT).show()
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(requireActivity(), "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
