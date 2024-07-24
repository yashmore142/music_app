package com.example.musicapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.apisetup.Repository
import com.example.musicapp.tracks.GetTrackResponse
import com.example.musicapp.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val repository: Repository,
    private val sessionManager: SessionManager
) : ViewModel() {
    var errorData: MutableLiveData<String> = MutableLiveData("")
    private val coroutineException = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorData.value = throwable.message
    }

    private var trackJob: Job? = null
    var _trackResponse: MutableLiveData<GetTrackResponse> = MutableLiveData(GetTrackResponse())

    val trackResponse: LiveData<GetTrackResponse> =
        _trackResponse

    fun getTrack(uid: String) {

        trackJob = viewModelScope.launch(coroutineException) {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getTrack(uid, sessionManager.getUserToken().toString())
                }

                _trackResponse.value = response



            } catch (e: Exception) {
                throw e
            }
        }


    }
}