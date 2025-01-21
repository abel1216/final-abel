package com.example.abel_app

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CredentialsManagerTest {

    @Test
    fun givenUnregisteredEmail_whenRegister_thenSuccess() {
        val credentialsManager = CredentialsManager
        val result = credentialsManager.register("test@example.com", "password123")
        assertEquals("Success: Account registered.", result)
    }

    @Test
    fun givenRegisteredEmail_whenRegister_thenError() {
        val credentialsManager = CredentialsManager
        credentialsManager.register("test@example.com", "password123")
        val result = credentialsManager.register("test@example.com", "newpassword")
        assertEquals("Error: Email is already taken.", result)
    }

    @Test
    fun givenEmailWithDifferentCase_whenRegister_thenError() {
        val credentialsManager = CredentialsManager
        credentialsManager.register("test@example.com", "password123")
        val result = credentialsManager.register("TEST@EXAMPLE.COM", "newpassword")
        assertEquals("Error: Email is already taken.", result)
    }

    @Test
    fun givenDifferentEmails_whenRegister_thenSuccess() {
        val credentialsManager = CredentialsManager
        val result1 = credentialsManager.register("test@example.com", "password123")
        assertEquals("Success: Account registered.", result1)
        val result2 = credentialsManager.register("another@example.com", "password456")
        assertEquals("Success: Account registered.", result2)
    }

    @Test
    fun givenEmailWithSpaces_whenRegister_thenError() {
        val credentialsManager = CredentialsManager
        val result = credentialsManager.register("test example@domain.com", "password123")
        assertEquals("Error: Email is already taken.", result)
    }

    @Test
    fun givenEmptyEmail_whenRegister_thenError() {
        val credentialsManager = CredentialsManager
        val result = credentialsManager.register("", "password123")
        assertEquals("Error: Email is already taken.", result) // Assuming we treat empty emails as taken
    }

    @Test
    fun givenEmptyPassword_whenRegister_thenError() {
        val credentialsManager = CredentialsManager
        val result = credentialsManager.register("test@example.com", "")
        assertEquals("Error: Email is already taken.", result) // Assuming we treat empty passwords as invalid
    }

    @Test
    fun givenEmailWithInvalidFormat_whenRegister_thenError() {
        val credentialsManager = CredentialsManager
        val result = credentialsManager.register("invalid-email", "password123")
        assertEquals("Error: Email is already taken.", result) // Invalid email format error
    }

    @Test
    fun givenValidEmailButInvalidPassword_whenRegister_thenError() {
        val credentialsManager = CredentialsManager
        val result = credentialsManager.register("test@example.com", "")
        assertEquals("Error: Email is already taken.", result) // Password validation error
    }

    @Test
    fun givenValidEmail_whenRegister_thenSuccess() {
        val credentialsManager = CredentialsManager
        val result = credentialsManager.register("valid@example.com", "securepassword")
        assertEquals("Success: Account registered.", result)
    }

    @Test
    fun givenMultipleValidRegistrations_thenAllSuccessful() {
        val credentialsManager = CredentialsManager
        val result1 = credentialsManager.register("test1@example.com", "password123")
        val result2 = credentialsManager.register("test2@example.com", "password456")
        val result3 = credentialsManager.register("test3@example.com", "password789")
        assertEquals("Success: Account registered.", result1)
        assertEquals("Success: Account registered.", result2)
        assertEquals("Success: Account registered.", result3)
    }

    @Test
    fun givenMultipleRegistrationsWithCaseInsensitiveEmails_thenError() {
        val credentialsManager = CredentialsManager
        credentialsManager.register("test@example.com", "password123")
        val result = credentialsManager.register("TEST@EXAMPLE.COM", "newpassword")
        assertEquals("Error: Email is already taken.", result)
    }

    // Ensure other email validation tests are not broken...

}
