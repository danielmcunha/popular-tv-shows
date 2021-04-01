package com.danielcunha.topseries.data.remote.repository

import com.danielcunha.topseries.data.remote.ApiService
import com.danielcunha.topseries.data.remote.SimpleResult
import com.danielcunha.topseries.data.remote.responses.TvListResponse
import com.danielcunha.topseries.data.remote.safe
import com.danielcunha.topseries.domain.repository.TvRepository

class TvRepositoryImpl(private val apiService: ApiService) : TvRepository {

    override suspend fun getPopularTvList(page: Int): SimpleResult<TvListResponse> {
        return safe {
            apiService.getPopularTvShows(page)
        }
    }
}