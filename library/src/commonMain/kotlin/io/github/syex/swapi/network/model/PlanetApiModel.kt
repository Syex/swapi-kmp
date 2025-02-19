package io.github.syex.swapi.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** The API model for a planet. */
@Serializable
data class PlanetApiModel(
    val climate: String,
    val created: Instant,
    val diameter: String,
    val edited: Instant,
    @SerialName("films")
    val filmUrls: List<String>,
    val gravity: String,
    val name: String,
    @SerialName("orbital_period")
    val orbitalPeriod: String,
    val population: String,
    @SerialName("residents")
    val residentUrls: List<String>,
    @SerialName("rotation_period")
    val rotationPeriod: String,
    @SerialName("surface_water")
    val surfaceWater: String,
    val terrain: String,
    val url: String
)

/**
 * An _expanded_ model contains the originally requested planet data along with additional
 * details about their films and residents.
 */
data class ExpandedPlanetApiModel(
    val original: PlanetApiModel,
    val films: List<FilmApiModel>,
    val residents: List<PeopleApiModel>,
)