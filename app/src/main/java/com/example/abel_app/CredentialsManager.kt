package com.example.abel_app

import android.content.Context
import android.content.SharedPreferences

object CredentialsManager {
    private const val PREFS_NAME = "UserPrefs"
    private const val USERS_KEY = "users" // JSON string of registered users

    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun register(email: String, password: String): Boolean {
        val users = getUsers()
        if (users.containsKey(email)) return false

        users[email] = password
        saveUsers(users)
        return true
    }

    fun validateCredentials(email: String, password: String): Boolean {
        val users = getUsers()
        return users[email] == password
    }

    private fun getUsers(): MutableMap<String, String> {
        val json = sharedPreferences.getString(USERS_KEY, null) ?: "{}"
        return jsonToMap(json)
    }

    private fun saveUsers(users: Map<String, String>) {
        val json = mapToJson(users)
        sharedPreferences.edit().putString(USERS_KEY, json).apply()
    }

    private fun jsonToMap(json: String): MutableMap<String, String> {
        return try {
            val map = mutableMapOf<String, String>()
            val jsonObject = org.json.JSONObject(json)
            jsonObject.keys().forEach { key -> map[key] = jsonObject.getString(key) }
            map
        } catch (e: Exception) {
            mutableMapOf()
        }
    }

    private fun mapToJson(map: Map<String, String>): String {
        val jsonObject = org.json.JSONObject()
        map.forEach { (key, value) -> jsonObject.put(key, value) }
        return jsonObject.toString()
    }
}
