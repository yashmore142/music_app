package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.musicapp.MainActivity
import com.example.musicapp.R
import com.example.musicapp.utils.Constants
import com.example.musicapp.utils.SessionManager
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var sessionManager: SessionManager
    val handler = Handler()
    private val REQUEST_CODE = 1337

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (sessionManager.getUserToken() != null) {
            handler.postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        } else {
            authenticateSpotify()
        }

    }

    private fun authenticateSpotify() {
        val builder = AuthorizationRequest.Builder(
            Constants.CLIENT_ID,
            AuthorizationResponse.Type.TOKEN,
            Constants.REDIRECT_URI
        )

        builder.setScopes(arrayOf("user-read-private", "user-read-email"))
        val request = builder.build()

        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    val accessToken = response.accessToken
                    Log.i("token", accessToken)
                    sessionManager.saveUserToken("Bearer $accessToken")
                    Log.i("session", sessionManager.getUserToken().toString())
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                AuthorizationResponse.Type.ERROR -> {
                    Log.i("token", "error")

                }
                else -> {
                    // Handle other cases
                }
            }
        }
    }

}