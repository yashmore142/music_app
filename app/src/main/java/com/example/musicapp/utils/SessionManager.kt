package com.example.musicapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.musicapp.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveUserToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getUserToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
    fun clearPrefs() {
        prefs.edit().clear().apply()
    }
}