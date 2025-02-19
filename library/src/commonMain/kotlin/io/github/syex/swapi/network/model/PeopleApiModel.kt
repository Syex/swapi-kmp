package io.github.syex.swapi.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** The API model for a person. */
@Serializable
data class PeopleApiModel(
    @SerialName("birth_year")
    val birthYear: String,
    @SerialName("eye_color")
    val eyeColor: String,
    @SerialName("films")
    val filmUrls: List<String>,
    val gender: String,
    @SerialName("hair_color")
    val hairColor: String,
    val height: String,
    @SerialName("homeworld")
    val homeworldUrl: String,
    val mass: String,
    @SerialName("name")
    val name: String,
    @SerialName("skin_color")
    val skinColor: String,
    val created: Instant,
    val edited: Instant,
    @SerialName("species")
    val speciesUrls: List<String>,
    @SerialName("starships")
    val starshipUrls: List<String>,
    val url: String,
    @SerialName("vehicles")
    val vehicleUrls: List<String>
)

/**
 * An _expanded_ model contains the originally requested people data along with additional
 * details about their films, homeworld species, starships, and vehicles.
 */
data class ExpandedPeopleApiModel(
    val original: PeopleApiModel,
    val films: List<FilmApiModel>,
    val homeworld: PlanetApiModel,
    val species: List<SpeciesApiModel>,
    val starships: List<StarshipApiModel>,
    val vehicles: List<VehicleApiModel>,
)