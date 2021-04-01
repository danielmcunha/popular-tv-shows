package com.danielcunha.topseries.core.injection

import com.danielcunha.topseries.data.remote.repository.TvRepositoryImpl
import com.danielcunha.topseries.domain.repository.TvRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<TvRepository> { TvRepositoryImpl(get()) }
}