// CredentialsManager.kt
package com.example.abel_app

class CredentialsManager {

    private val accounts = mutableMapOf<String, String>()

    init {
        // Adding a default account for testing
        accounts["test@te.st"] = "1234"
    }

    fun register(email: String, password: String): Boolean {
        if (accounts.containsKey(email.lowercase())) return false
        accounts[email.lowercase()] = password
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        // Example password validation: Minimum 6 characters
        return password.length >= 8
    }
}
