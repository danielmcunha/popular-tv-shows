package com.danielcunha.topseries.domain.usecases

import com.danielcunha.topseries.data.remote.SimpleResult

interface BaseUseCase<in Parameter, out Result> {

    suspend operator fun invoke(params: Parameter): SimpleResult<Result>
}