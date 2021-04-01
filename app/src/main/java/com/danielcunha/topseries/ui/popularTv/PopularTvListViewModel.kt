package com.danielcunha.topseries.ui.popularTv

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.danielcunha.topseries.domain.usecases.GetPopularTvListUseCase
import com.danielcunha.topseries.ui.base.BaseViewModel
import com.danielcunha.topseries.ui.popularTv.adapter.PopularTvAdpter
import com.danielcunha.topseries.ui.popularTv.adapter.TvItemViewHolder
import kotlinx.coroutines.launch
import org.koin.core.inject

class PopularTvListViewModel(app: Application) : BaseViewModel(app) {

    private val getPopularTvListUseCase: GetPopularTvListUseCase by inject()

    var page = 1
    val adapter = PopularTvAdpter()

    fun reload() {
        page = 0
        load()
    }

    fun load() {
        viewModelScope.launch {
            isLoading.value = true

            getPopularTvListUseCase(page).fold(
                success = { response ->
                    adapter.addItems(
                        response.results.map { result ->
                            TvItemViewHolder(
                                result.name,
                                result.posterPath.orEmpty(),
                                result.voteAverage.toFloat(),
                                result.firstAirDate
                            )
                        }
                    )
                },
                failure = {
                    errorMessage.value = it.message
                }
            )
        }
    }
}