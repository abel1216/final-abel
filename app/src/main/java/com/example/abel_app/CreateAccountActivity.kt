package com.example.abel_app

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CreateAccountActivity : AppCompatActivity() {

    private val credentialsManager = CredentialsManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        // Verify all IDs match the layout file
        val fullnameInputLayout by lazy { findViewById<TextInputLayout>(R.id.fullNameInput) }
        val phoneInputLayout by lazy { findViewById<TextInputLayout>(R.id.phoneInput) }
        val fullnameEditText by lazy { findViewById<TextInputLayout>(R.id.fullNameInput) }
        val phoneEditText by lazy { findViewById<TextInputLayout>(R.id.phoneInput) }
        val emailInputLayout by lazy { findViewById<TextInputLayout>(R.id.emailInputLayout) }
        val passwordInputLayout by lazy { findViewById<TextInputLayout>(R.id.passwordInputLayout) }
        val emailEditText by lazy { findViewById<TextInputEditText>(R.id.emailEditText) }
        val passwordEditText by lazy { findViewById<TextInputEditText>(R.id.passwordEditText)}
        val nextButton by lazy { findViewById<Button>(R.id.nextButton)}
        val loggingButton by lazy { findViewById<Button>(R.id.loggingButton)}


        nextButton?.setOnClickListener {
            val fullname = fullnameEditText.toString().trim()
            val phone = phoneEditText.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            if (fullname.isEmpty()) {
                fullnameInputLayout.error = "Full name cannot be empty"
                return@setOnClickListener
            } else {
                fullnameInputLayout.error = null
            }

            if (phone.isEmpty()) {
                phoneInputLayout.error = "Phone number cannot be empty"
                return@setOnClickListener
            } else if (!Patterns.PHONE.matcher(phone).matches()) {
                phoneInputLayout.error = "Invalid phone number format"
                return@setOnClickListener
            } else {
                phoneInputLayout.error = null
            }

            if (email.isEmpty()) {
                emailInputLayout.error = "Email cannot be empty"
                return@setOnClickListener
            } else {
                if (password.isEmpty()) {
                    passwordInputLayout.error = "Password cannot be empty"
                    return@setOnClickListener
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailInputLayout.error = "Invalid email format"
                    return@setOnClickListener
                }
            }

            loggingButton.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

            // Attempt registration
            val registrationResult = credentialsManager.register(email , password)
            if (registrationResult) {
                // Successful registration
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                // Email already exists
                emailInputLayout?.error = "Email is already registered"
            }
        }
    }
}

