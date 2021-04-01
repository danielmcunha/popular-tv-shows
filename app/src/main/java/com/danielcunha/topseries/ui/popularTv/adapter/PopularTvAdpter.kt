package com.danielcunha.topseries.ui.popularTv.adapter

import com.danielcunha.topseries.ui.base.BaseAdapter

class PopularTvAdpter : BaseAdapter<TvItemViewHolder>() {

    override fun getLayoutIdForPosition(position: Int): Int {
        TODO("Not yet implemented")
    }
}

data class TvItemViewHolder(
    val title: String,
    val posterPath: String,
    val voteAverage: Float,
    val firstAirYear: String
)