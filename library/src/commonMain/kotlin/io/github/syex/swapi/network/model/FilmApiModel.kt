package io.github.syex.swapi.network.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** The API model for a film. */
@Serializable
data class FilmApiModel(
    val characters: List<String>,
    val created: Instant,
    val director: String,
    val edited: Instant,
    @SerialName("episode_id")
    val episodeId: Int,
    @SerialName("opening_crawl")
    val openingCrawl: String,
    @SerialName("planets")
    val planetUrls: List<String>,
    val producer: String,
    @SerialName("release_date")
    val releaseDate: LocalDate,
    @SerialName("species")
    val speciesUrls: List<String>,
    @SerialName("starships")
    val starshipUrls: List<String>,
    val title: String,
    val url: String,
    @SerialName("vehicles")
    val vehicleUrls: List<String>
)

/**
 * An _expanded_ model contains the originally requested film data along with additional
 * details about its planets, species, starships, and vehicles.
 */
data class ExpandedFilmApiModel(
    val original: FilmApiModel,
    val planets: List<PlanetApiModel>,
    val species: List<SpeciesApiModel>,
    val starships: List<StarshipApiModel>,
    val vehicles: List<VehicleApiModel>,
)