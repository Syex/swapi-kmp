package io.github.syex.swapi.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** The API model for a species. */
@Serializable
data class SpeciesApiModel(
    @SerialName("average_height")
    val averageHeight: String,
    @SerialName("average_lifespan")
    val averageLifespan: String,
    val classification: String,
    val created: Instant,
    val designation: String,
    val edited: Instant,
    @SerialName("eye_colors")
    val eyeColors: String,
    @SerialName("hair_colors")
    val hairColors: String,
    @SerialName("homeworld")
    val homeworldUrl: String,
    val language: String,
    val name: String,
    @SerialName("people")
    val peopleUrls: List<String>,
    @SerialName("films")
    val filmUrls: List<String>,
    @SerialName("skin_colors")
    val skinColors: String,
    val url: String
)

/**
 * An _expanded_ model contains the originally requested species data along with additional
 * details about their homeworld, people, and films.
 */
data class ExpandedSpeciesApiModel(
    val original: SpeciesApiModel,
    val homeworld: PlanetApiModel,
    val people: List<PeopleApiModel>,
    val films: List<FilmApiModel>,
)