package com.danielcunha.topseries.data.remote.responses

import com.google.gson.annotations.SerializedName

data class TvListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val results: List<TvListResult>
)

data class TvListResult(
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("popularity") val popularity: Number,
    @SerializedName("id") val id: Number,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: Number,
    @SerializedName("overview") val overview: String,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("genre_ids") val genreIds: List<Long>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("vote_count") val voteCount: String,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String
)