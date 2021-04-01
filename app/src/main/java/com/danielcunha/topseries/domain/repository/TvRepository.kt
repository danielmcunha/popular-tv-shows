package com.danielcunha.topseries.domain.repository

import com.danielcunha.topseries.data.remote.SimpleResult
import com.danielcunha.topseries.data.remote.responses.TvListResponse

interface TvRepository {

    suspend fun getPopularTvList(page: Int) : SimpleResult<TvListResponse>
}