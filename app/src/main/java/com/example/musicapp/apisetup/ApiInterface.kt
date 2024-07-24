package com.example.musicapp.apisetup

import com.example.musicapp.tracks.GetTrackResponse
import com.example.musicapp.view.search.model.SearchReasponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("v1/tracks/{id}")
    fun getTracks(
        @Path("id") ids: String
    ):Call<GetTrackResponse>
    @GET("v1/search")
    fun getSearch(
        @Query("q") q: String,
        @Query("type") type : String
    ):Call<SearchReasponse>
}