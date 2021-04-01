package com.danielcunha.topseries.domain.usecases

import com.danielcunha.topseries.data.remote.SimpleResult
import com.danielcunha.topseries.data.remote.responses.TvListResponse
import com.danielcunha.topseries.domain.repository.TvRepository

class GetPopularTvListUseCase(
    private val tvRepository: TvRepository,
) : BaseUseCase<Int, TvListResponse> {

    override suspend operator fun invoke(params: Int): SimpleResult<TvListResponse> {
        return tvRepository.getPopularTvList(params)
    }
}