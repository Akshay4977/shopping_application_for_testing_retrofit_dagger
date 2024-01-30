package com.example.shoppingapplicationfortestingretrofitdagger.data.remote


import com.example.shoppingapplicationfortestingretrofitdagger.BuildConfig
import com.example.shoppingapplicationfortestingretrofitdagger.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @retrofit2.http.Query("q") searchQuery: String,
        @retrofit2.http.Query("key") apiKey: String = "41579060-a99d17159e8e366086561d22a"
    ): Response<ImageResponse>
}