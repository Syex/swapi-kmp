package io.github.syex.swapi.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleApiModel(
    @SerialName("birth_year")
    val birthYear: String,

    @SerialName("eye_color")
    val eyeColor: String,

    @SerialName("films")
    val filmUrls: List<String>,

    @SerialName("gender")
    val gender: String,

    @SerialName("hair_color")
    val hairColor: String,

    @SerialName("height")
    val height: String,

    @SerialName("homeworld")
    val homeworldUrl: String,

    @SerialName("mass")
    val mass: String,

    @SerialName("name")
    val name: String,

    @SerialName("skin_color")
    val skinColor: String,

    @SerialName("created")
    val created: Instant,

    @SerialName("edited")
    val edited: Instant,

    @SerialName("species")
    val speciesUrls: List<String>,

    @SerialName("starships")
    val starshipUrls: List<String>,

    @SerialName("url")
    val url: String,

    @SerialName("vehicles")
    val vehicleUrls: List<String>
)