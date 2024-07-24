package com.example.musicapp.tracks

data class GetTrackResponse(
    var album: Album? = null,
    var artists: List<ArtistX>? = null,
    var available_markets: List<Any>? = null,
    var disc_number: Int? = null,
    var duration_ms: Int? = null,
    var explicit: Boolean? = null,
    var external_ids: ExternalIds? = null,
    var external_urls: ExternalUrlsXXX? = null,
    var href: String? = null,
    var id: String? = null,
    var is_local: Boolean? = null,
    var name: String? = null,
    var popularity: Int? = null,
    var preview_url: Any? = null,
    var track_number: Int? = null,
    var type: String? = null,
    var uri: String? = null
)