package com.example.musicapp.view.search.model

data class Tracks(
    var href: String?,
    var items: ArrayList<Item>?,
    var limit: Int?,
    var next: String?,
    var offset: Int?,
    var previous: Any?,
    var total: Int?
)