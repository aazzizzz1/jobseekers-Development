package com.example.jobseeker.fragment

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            var email = binding.lEmail.text.toString()
            var password = binding.lPassword.text.toString()

            if (email.isEmpty()){
                binding.lEmail.error = "email harus diisi"
                binding.lEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.lEmail.error = "email tidak valid"
                binding.lEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.lPassword.error = "Password harus diisi"
                binding.lPassword.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6){
                binding.lPassword.error = "Password harus 6 karakter atau lebih"
                binding.lPassword.requestFocus()
                return@setOnClickListener
            }

            dataUserLogin(email, password)
        }

        binding.textLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return binding.root
    }

    private fun dataUserLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) {
            if (it.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(requireActivity(), " $email berhasil login", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_id_home_fragment)
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(requireActivity(), "Gagal untuk login cek your $email and $password", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}