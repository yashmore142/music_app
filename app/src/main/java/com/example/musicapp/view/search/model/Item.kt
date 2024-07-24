package com.example.musicapp.view.search.model

data class Item(
    var album: Album?,
    var artists: List<ArtistX>?,
    var available_markets: List<String>?,
    var disc_number: Int?,
    var duration_ms: Int?,
    var explicit: Boolean?,
    var external_ids: ExternalIds?,
    var external_urls: ExternalUrlsXXX?,
    var href: String?,
    var id: String?,
    var is_local: Boolean?,
    var name: String?,
    var popularity: Int?,
    var preview_url: String?,
    var track_number: Int?,
    var type: String?,
    var uri: String?
)