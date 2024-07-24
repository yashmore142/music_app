package com.example.musicapp.apisetup

import com.example.musicapp.tracks.GetTrackResponse
import com.example.musicapp.view.search.model.SearchReasponse
import retrofit2.await

class RepositoryImpl(private val apiInterface: ApiInterface) : Repository {
    override suspend fun getTrack(ids: String,token : String): GetTrackResponse {
        return apiInterface.getTracks(ids).await()
    }

    override suspend fun getSearch(q: String, type: String): SearchReasponse {
        return apiInterface.getSearch(q,type).await()
    }
}