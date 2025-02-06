package com.example.retrofitpoc.network

import com.example.retrofitpoc.model.NYTMostViewed
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val API_KEY = "HthmaPPzX22aIV2nI99hhfaLnKBQR4C6"

object RetrofitClient {
    private const val BASE_URL = "https://api.nytimes.com/svc/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("mostpopular/v2/viewed/1.json?api-key=$API_KEY")
    suspend fun getArticles() : NYTMostViewed
}