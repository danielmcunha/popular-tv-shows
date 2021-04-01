package com.danielcunha.topseries.core.injection

import com.danielcunha.topseries.domain.repository.TvRepository
import com.danielcunha.topseries.domain.usecases.GetPopularTvListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { provideGetPopularTvListUseCase(get()) }
}

fun provideGetPopularTvListUseCase(tvRepository: TvRepository): GetPopularTvListUseCase {
    return GetPopularTvListUseCase(tvRepository)
}