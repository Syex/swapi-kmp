package io.github.syex.swapi.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleApiModel(
    @SerialName("cargo_capacity")
    val cargoCapacity: String,
    val consumables: String,
    @SerialName("cost_in_credits")
    val costInCredits: String,
    val created: Instant,
    val crew: String,
    val edited: Instant,
    val length: String,
    val manufacturer: String,
    @SerialName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,
    val model: String,
    val name: String,
    val passengers: String,
    @SerialName("pilots")
    val pilotUrls: List<String>,
    @SerialName("films")
    val filmUrls: List<String>,
    val url: String,
    @SerialName("vehicle_class")
    val vehicleClass: String
)