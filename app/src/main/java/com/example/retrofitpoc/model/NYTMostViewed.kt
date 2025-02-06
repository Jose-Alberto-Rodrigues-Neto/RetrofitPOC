package com.example.retrofitpoc.model

data class NYTMostViewed(
    val status: String,
    val copyright: String,
    val numResults: Int,
    val results: List<Articles>
)
