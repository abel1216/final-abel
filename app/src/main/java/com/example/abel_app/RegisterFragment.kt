package com.example.abel_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    private val credentialsManager = CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // View Bindings
        val usernameField: EditText = view.findViewById(R.id.et_register_username)
        val passwordField: EditText = view.findViewById(R.id.et_register_password)
        val registerButton: Button = view.findViewById(R.id.btn_register)

        // Register Button Listener
        registerButton.setOnClickListener {
            val email = usernameField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty()) {
                usernameField.error = "Email cannot be empty"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordField.error = "Password cannot be empty"
                return@setOnClickListener
            }

            if (credentialsManager.register(email, password)) {
                Toast.makeText(requireContext(), "Registered Successfully", Toast.LENGTH_SHORT).show()
                // Navigate back to LoginFragment
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "Email already exists", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
