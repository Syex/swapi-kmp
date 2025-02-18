package io.github.syex.swapi

import io.github.syex.swapi.network.createDefaultHttpClientEngine
import io.github.syex.swapi.network.model.FilmApiModel
import io.github.syex.swapi.network.model.PagedResponse
import io.github.syex.swapi.network.model.PeopleApiModel
import io.github.syex.swapi.network.model.PlanetApiModel
import io.github.syex.swapi.network.model.SpeciesApiModel
import io.github.syex.swapi.network.model.StarshipApiModel
import io.github.syex.swapi.network.model.VehicleApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

@Suppress("unused")
class StarWarsAPIClient internal constructor(
    private val httpClient: HttpClient,
) {

    @Throws(Exception::class)
    suspend fun getAllPeople(): PagedResponse<PeopleApiModel> =
        httpClient.get("$BASE_API/people").body()

    @Throws(Exception::class)
    suspend fun getPeopleByUrl(url: String): PeopleApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getAllFilms(): PagedResponse<FilmApiModel> =
        httpClient.get("$BASE_API/films").body()

    @Throws(Exception::class)
    suspend fun getFilmByUrl(url: String): FilmApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getAllStarships(): PagedResponse<StarshipApiModel> =
        httpClient.get("$BASE_API/starships").body()

    @Throws(Exception::class)
    suspend fun getStarshipByUrl(url: String): StarshipApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getAllVehicles(): PagedResponse<VehicleApiModel> =
        httpClient.get("$BASE_API/vehicles").body()

    @Throws(Exception::class)
    suspend fun getVehicleByUrl(url: String): VehicleApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getAllSpecies(): PagedResponse<SpeciesApiModel> =
        httpClient.get("$BASE_API/species").body()

    @Throws(Exception::class)
    suspend fun getSpeciesByUrl(url: String): SpeciesApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getAllPlanets(): PagedResponse<PlanetApiModel> =
        httpClient.get("$BASE_API/planets").body()

    @Throws(Exception::class)
    suspend fun getPlanetByUrl(url: String): PlanetApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun <T> loadNextPage(pageUrl: String): PagedResponse<T> = httpClient.get(pageUrl).body()

    companion object Builder {
        private const val BASE_API = "https://swapi.dev/api"

        fun create(block: StarWarsAPIClientConfig.() -> Unit): StarWarsAPIClient {
            val config = StarWarsAPIClientConfig().apply(block)
            val client = HttpClient(config.httpClientEngine ?: createDefaultHttpClientEngine()) {
                install(HttpTimeout) {
                    connectTimeoutMillis = config.connectTimeoutMs
                    requestTimeoutMillis = config.requestTimeoutMs
                    socketTimeoutMillis = config.socketTimeoutMs
                }

                install(ContentNegotiation) {
                    json(config.jsonConfig)
                }

                config.additionalConfig(this)
            }

            return StarWarsAPIClient(client)
        }
    }
}
