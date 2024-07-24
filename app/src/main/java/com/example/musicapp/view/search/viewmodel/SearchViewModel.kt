package com.example.musicapp.view.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.apisetup.Repository
import com.example.musicapp.view.search.model.SearchReasponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    var errorData: MutableLiveData<String> = MutableLiveData("")
    private val coroutineException = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorData.value = throwable.message
    }

    private var searchJob: Job? = null
    private var _searchResponse: MutableLiveData<SearchReasponse> =
        MutableLiveData(SearchReasponse())

    val searchResponse: LiveData<SearchReasponse> =
        _searchResponse

    fun getSearch(q: String, type: String) {

        searchJob = viewModelScope.launch(coroutineException) {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getSearch(q, type)
                }
                _searchResponse.value = response
            } catch (e: Exception) {
                throw e
            }
        }


    }

}