package com.example.dagger_hilt_demo.api_call_setup

import android.util.Log
import com.example.musicapp.utils.SessionManager
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class AuthIntersepter(val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.i("AutSess",sessionManager.getUserToken().toString())
        val request = chain.request()
        val authRequest = request.newBuilder()
            .header("Authorization", sessionManager.getUserToken().toString())
            .build()
        return chain.proceed(authRequest)
    }
}