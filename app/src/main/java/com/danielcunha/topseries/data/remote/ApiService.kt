package com.danielcunha.topseries.data.remote

import com.danielcunha.topseries.data.remote.responses.TvListResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/tv/popular")
    suspend fun getPopularTvShows(@Query("page") page: Int): TvListResponse
}