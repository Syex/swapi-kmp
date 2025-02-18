package io.github.syex.swapi.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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