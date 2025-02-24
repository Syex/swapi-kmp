package io.github.syex.swapi.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** The API model for a starship. */
@Serializable
data class StarshipApiModel(
    @SerialName("MGLT")
    val mglt: String,
    @SerialName("cargo_capacity")
    val cargoCapacity: String,
    val consumables: String,
    @SerialName("cost_in_credits")
    val costInCredits: String,
    val created: Instant,
    val crew: String,
    val edited: Instant,
    @SerialName("hyperdrive_rating")
    val hyperdriveRating: String,
    val length: String,
    val manufacturer: String,
    @SerialName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,
    val model: String,
    val name: String,
    val passengers: String,
    @SerialName("films")
    val filmUrls: List<String>,
    @SerialName("pilots")
    val pilotUrls: List<String>,
    @SerialName("starship_class")
    val starshipClass: String,
    val url: String,
)

/**
 * An _expanded_ model contains the originally requested starship data along with additional
 * details about their films and pilots.
 */
data class ExpandedStarshipApiModel(
    val original: StarshipApiModel,
    val films: List<FilmApiModel>,
    val pilots: List<PeopleApiModel>,
)