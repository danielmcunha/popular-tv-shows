package com.danielcunha.topseries.core.injection

import com.danielcunha.topseries.ui.popularTv.PopularTvListViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {

    viewModel { PopularTvListViewModel(get()) }
}