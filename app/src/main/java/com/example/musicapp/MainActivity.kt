package com.example.musicapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.utils.Constants.CLIENT_ID
import com.example.musicapp.utils.Constants.REDIRECT_URI
import com.example.musicapp.utils.SessionManager
import com.example.musicapp.view.search.fagment.SearchFragment
import com.example.musicapp.viewmodel.TrackViewModel
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<TrackViewModel>()
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentChange(SearchFragment(), "search_fragment")

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search_menu -> {
                    fragmentChange(SearchFragment(), "search_fragment")
                    true
                }
                R.id.account_menu -> {

                    true
                }

                else -> {
                    false
                }
            }
        }

    }
    private fun fragmentChange(fragment: Fragment, tag: String) {
        this.supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.nav_main_container, fragment, tag)
            .commit()
    }


}