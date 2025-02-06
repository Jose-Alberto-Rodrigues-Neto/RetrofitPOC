package com.example.retrofitpoc.model

import com.google.gson.annotations.SerializedName

data class Media(
    val type: String,
    val subtype: String,
    val caption: String,
    val copyright: String,
    val approved_for_syndication: Int,
    @SerializedName("media-metadata")
    val mediametadata: List<MediaMetaData>
)
