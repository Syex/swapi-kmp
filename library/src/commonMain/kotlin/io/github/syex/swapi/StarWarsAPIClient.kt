package io.github.syex.swapi

import io.github.syex.swapi.network.createDefaultHttpClientEngine
import io.github.syex.swapi.network.model.ExpandedFilmApiModel
import io.github.syex.swapi.network.model.ExpandedPeopleApiModel
import io.github.syex.swapi.network.model.ExpandedPlanetApiModel
import io.github.syex.swapi.network.model.ExpandedSpeciesApiModel
import io.github.syex.swapi.network.model.ExpandedStarshipApiModel
import io.github.syex.swapi.network.model.ExpandedVehicleApiModel
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
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * Provides all entry points to the Star Wars API. Get an instance via [StarWarsAPIClient.create].
 *
 * Supported use cases are:
 * - Searching on a specific endpoint with a query.
 * - Fetching all entries of a specific endpoint.
 * - Fetching a single entry of a specific endpoint by passing a URL.
 *
 * Additionally, the library introduces the concept of an _expanded_ model. A normal API response
 * contains hyperlinks to other resources, but fetching an _expanded_ model has those hyperlinks
 * already resolved.
 */
@Suppress("unused")
class StarWarsAPIClient internal constructor(
    private val httpClient: HttpClient,
) {

    @Throws(Exception::class)
    suspend fun searchPeople(query: String): PagedResponse<PeopleApiModel> =
        httpClient.get("$BASE_API/$PATH_PEOPLE") {
            url {
                parameters.append("search", query.trim())
            }
        }.body()

    @Throws(Exception::class)
    suspend fun getAllPeople(pageUrl: String? = null): PagedResponse<PeopleApiModel> =
        httpClient.get(pageUrl ?: "$BASE_API/$PATH_PEOPLE").body()

    @Throws(Exception::class)
    suspend fun getPeopleByUrl(url: String): PeopleApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getExpandedPeopleByUrl(url: String): ExpandedPeopleApiModel {
        val peopleApiModel = getPeopleByUrl(url)

        return coroutineScope {
            val films = async { peopleApiModel.filmUrls.map { getFilmByUrl(it) } }
            val homeworld = async { getPlanetByUrl(peopleApiModel.homeworldUrl) }
            val species = async { peopleApiModel.speciesUrls.map { getSpeciesByUrl(it) } }
            val starships = async { peopleApiModel.starshipUrls.map { getStarshipByUrl(it) } }
            val vehicles = async { peopleApiModel.vehicleUrls.map { getVehicleByUrl(it) } }

            return@coroutineScope ExpandedPeopleApiModel(
                original = peopleApiModel,
                films = films.await(),
                homeworld = homeworld.await(),
                species = species.await(),
                starships = starships.await(),
                vehicles = vehicles.await(),
            )
        }
    }

    @Throws(Exception::class)
    suspend fun searchFilms(query: String): PagedResponse<FilmApiModel> =
        httpClient.get("$BASE_API/$PATH_FILMS") {
            url {
                parameters.append("search", query.trim())
            }
        }.body()

    @Throws(Exception::class)
    suspend fun getAllFilms(pageUrl: String? = null): PagedResponse<FilmApiModel> =
        httpClient.get(pageUrl ?: "$BASE_API/$PATH_FILMS").body()

    @Throws(Exception::class)
    suspend fun getFilmByUrl(url: String): FilmApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getExpandedFilmByUrl(url: String): ExpandedFilmApiModel {
        val filmApiModel = getFilmByUrl(url)

        return coroutineScope {
            val planets = async { filmApiModel.planetUrls.map { getPlanetByUrl(it) } }
            val species = async { filmApiModel.speciesUrls.map { getSpeciesByUrl(it) } }
            val starships = async { filmApiModel.starshipUrls.map { getStarshipByUrl(it) } }
            val vehicles = async { filmApiModel.vehicleUrls.map { getVehicleByUrl(it) } }

            return@coroutineScope ExpandedFilmApiModel(
                original = filmApiModel,
                planets = planets.await(),
                species = species.await(),
                starships = starships.await(),
                vehicles = vehicles.await(),
            )
        }
    }

    @Throws(Exception::class)
    suspend fun searchStarships(query: String): PagedResponse<StarshipApiModel> =
        httpClient.get("$BASE_API/$PATH_STARSHIPS") {
            url {
                parameters.append("search", query.trim())
            }
        }.body()

    @Throws(Exception::class)
    suspend fun getAllStarships(pageUrl: String? = null): PagedResponse<StarshipApiModel> =
        httpClient.get(pageUrl ?: "$BASE_API/$PATH_STARSHIPS").body()

    @Throws(Exception::class)
    suspend fun getStarshipByUrl(url: String): StarshipApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getExpandedStarshipByUrl(url: String): ExpandedStarshipApiModel {
        val starshipApiModel = getStarshipByUrl(url)

        return coroutineScope {
            val films = async { starshipApiModel.filmUrls.map { getFilmByUrl(it) } }
            val pilots = async { starshipApiModel.pilotUrls.map { getPeopleByUrl(it) } }

            return@coroutineScope ExpandedStarshipApiModel(
                original = starshipApiModel,
                films = films.await(),
                pilots = pilots.await(),
            )
        }
    }

    @Throws(Exception::class)
    suspend fun searchVehicles(query: String): PagedResponse<VehicleApiModel> =
        httpClient.get("$BASE_API/$PATH_VEHICLES") {
            url {
                parameters.append("search", query.trim())
            }
        }.body()

    @Throws(Exception::class)
    suspend fun getAllVehicles(pageUrl: String? = null): PagedResponse<VehicleApiModel> =
        httpClient.get(pageUrl ?: "$BASE_API/$PATH_VEHICLES").body()

    @Throws(Exception::class)
    suspend fun getVehicleByUrl(url: String): VehicleApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getExpandedVehicleByUrl(url: String): ExpandedVehicleApiModel {
        val vehicleApiModel = getVehicleByUrl(url)

        return coroutineScope {
            val films = async { vehicleApiModel.filmUrls.map { getFilmByUrl(it) } }
            val pilots = async { vehicleApiModel.pilotUrls.map { getPeopleByUrl(it) } }

            return@coroutineScope ExpandedVehicleApiModel(
                original = vehicleApiModel,
                films = films.await(),
                pilots = pilots.await(),
            )
        }
    }

    @Throws(Exception::class)
    suspend fun searchSpecies(query: String): PagedResponse<SpeciesApiModel> =
        httpClient.get("$BASE_API/$PATH_SPECIES") {
            url {
                parameters.append("search", query.trim())
            }
        }.body()

    @Throws(Exception::class)
    suspend fun getAllSpecies(pageUrl: String? = null): PagedResponse<SpeciesApiModel> =
        httpClient.get(pageUrl ?: "$BASE_API/$PATH_SPECIES").body()

    @Throws(Exception::class)
    suspend fun getSpeciesByUrl(url: String): SpeciesApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getExpandedSpeciesByUrl(url: String): ExpandedSpeciesApiModel {
        val speciesApiModel = getSpeciesByUrl(url)

        return coroutineScope {
            val homeworld = async { getPlanetByUrl(speciesApiModel.homeworldUrl) }
            val films = async { speciesApiModel.filmUrls.map { getFilmByUrl(it) } }
            val people = async { speciesApiModel.peopleUrls.map { getPeopleByUrl(it) } }

            return@coroutineScope ExpandedSpeciesApiModel(
                original = speciesApiModel,
                homeworld = homeworld.await(),
                films = films.await(),
                people = people.await(),
            )
        }
    }

    @Throws(Exception::class)
    suspend fun searchPlanets(query: String): PagedResponse<PlanetApiModel> =
        httpClient.get("$BASE_API/$PATH_PLANETS") {
            url {
                parameters.append("search", query.trim())
            }
        }.body()

    @Throws(Exception::class)
    suspend fun getAllPlanets(pageUrl: String? = null): PagedResponse<PlanetApiModel> =
        httpClient.get(pageUrl ?: "$BASE_API/$PATH_PLANETS").body()

    @Throws(Exception::class)
    suspend fun getPlanetByUrl(url: String): PlanetApiModel = httpClient.get(url).body()

    @Throws(Exception::class)
    suspend fun getExpandedPlanetByUrl(url: String): ExpandedPlanetApiModel {
        val planetApiModel = getPlanetByUrl(url)

        return coroutineScope {
            val films = async { planetApiModel.filmUrls.map { getFilmByUrl(it) } }
            val residents = async { planetApiModel.residentUrls.map { getPeopleByUrl(it) } }

            return@coroutineScope ExpandedPlanetApiModel(
                original = planetApiModel,
                films = films.await(),
                residents = residents.await(),
            )
        }
    }

    companion object Builder {
        private const val BASE_API = "https://swapi.dev/api"
        private const val PATH_PLANETS = "planets"
        private const val PATH_FILMS = "films"
        private const val PATH_PEOPLE = "people"
        private const val PATH_SPECIES = "species"
        private const val PATH_VEHICLES = "vehicles"
        private const val PATH_STARSHIPS = "starships"

        /**
         * Creates a new [StarWarsAPIClient].
         *
         * You can use [block] to configure the client, see [StarWarsAPIClientConfig].
         */
        fun create(block: StarWarsAPIClientConfig.() -> Unit = {}): StarWarsAPIClient {
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
