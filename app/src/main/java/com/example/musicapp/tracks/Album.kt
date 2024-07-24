package com.example.musicapp.tracks

data class Album(
    var album_type: String?,
    var artists: List<ArtistX>?,
    var available_markets: List<Any>?,
    var external_urls: ExternalUrlsXXX?,
    var href: String?,
    var id: String?,
    var images: List<Image>?,
    var name: String?,
    var release_date: String?,
    var release_date_precision: String?,
    var total_tracks: Int?,
    var type: String?,
    var uri: String?
)