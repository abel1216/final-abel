package com.example.abel_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // View Bindings
        val emailField: EditText = view.findViewById(R.id.emailEditText)
        val passwordField: EditText = view.findViewById(R.id.passwordEditText)
        val loginButton: Button = view.findViewById(R.id.loggingButton)
        val registerButton: TextView = view.findViewById(R.id.registerButton)

        // Login Button Listener
        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty()) {
                emailField.error = "Email cannot be empty"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordField.error = "Password cannot be empty"
                return@setOnClickListener
            }

            // Validate Credentials
            if (CredentialsManager.validateCredentials(email, password)) {
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()

                // Navigate to RecipeFragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RecipeFragment())
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        // Register Now Button Listener
        registerButton.setOnClickListener {
            // Navigate to RegisterFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .addToBackStack(null) // Add to backstack to allow navigation back
                .commit()
        }

        return view
    }
}

