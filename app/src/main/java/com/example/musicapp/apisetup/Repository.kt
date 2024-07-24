package com.example.musicapp.apisetup

import com.example.musicapp.tracks.GetTrackResponse
import com.example.musicapp.view.search.model.SearchReasponse

interface Repository {

    suspend fun getTrack(ids: String,token :String) : GetTrackResponse
    suspend fun getSearch(q: String,type : String) : SearchReasponse
}