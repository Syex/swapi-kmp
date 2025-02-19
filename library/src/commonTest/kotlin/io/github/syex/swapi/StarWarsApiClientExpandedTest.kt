package io.github.syex.swapi

import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

/** Extracted tests for all getExpanded*byUrl methods for better overview. */
class StarWarsApiClientExpandedTest {

    private val mockEngine = buildMockEngine()
    private val client = StarWarsAPIClient.create {
        httpClientEngine = mockEngine
    }

    @Test
    fun `getExpandedPeopleByUrl should return a ExpandedPeopleApiModel`() = runTest {
        val expandedPeopleApiModel =
            client.getExpandedPeopleByUrl("https://swapi.dev/api/people/1/")

        expandedPeopleApiModel.original.name shouldBe "Luke Skywalker"
        expandedPeopleApiModel.films.shouldNotBeEmpty()
        expandedPeopleApiModel.homeworld.name shouldBe "Tatooine"
        expandedPeopleApiModel.species.shouldNotBeEmpty()
        expandedPeopleApiModel.vehicles.shouldNotBeEmpty()
        expandedPeopleApiModel.starships.shouldNotBeEmpty()
    }

    @Test
    fun `getExpandedFilmByUrl should return an ExpandedFilmApiModel`() = runTest {
        val expandedFilmApiModel = client.getExpandedFilmByUrl("https://swapi.dev/api/films/1/")

        expandedFilmApiModel.original.title shouldBe "A New Hope"
        expandedFilmApiModel.planets.shouldNotBeEmpty()
        expandedFilmApiModel.species.shouldNotBeEmpty()
        expandedFilmApiModel.vehicles.shouldNotBeEmpty()
        expandedFilmApiModel.starships.shouldNotBeEmpty()
    }

    @Test
    fun `getExpandedStarshipByUrl should return an ExpandedStarshipApiModel`() = runTest {
        val expandedStarshipApiModel =
            client.getExpandedStarshipByUrl("https://swapi.dev/api/starships/1/")

        expandedStarshipApiModel.original.name shouldBe "Death Star"
        expandedStarshipApiModel.films.shouldNotBeEmpty()
        expandedStarshipApiModel.pilots.shouldNotBeEmpty()
    }

    @Test
    fun `getExpandedVehicleByUrl should return an ExpandedVehicleApiModel`() = runTest {
        val expandedVehicleApiModel =
            client.getExpandedVehicleByUrl("https://swapi.dev/api/vehicles/1/")

        expandedVehicleApiModel.original.name shouldBe "Sand Crawler"
        expandedVehicleApiModel.films.shouldNotBeEmpty()
        expandedVehicleApiModel.pilots.shouldNotBeEmpty()
    }

    @Test
    fun `getExpandedSpeciesByUrl should return an ExpandedSpeciesApiModel`() = runTest {
        val expandedSpeciesApiModel =
            client.getExpandedSpeciesByUrl("https://swapi.dev/api/speciess/1/")

        expandedSpeciesApiModel.original.name shouldBe "Wookie"
        expandedSpeciesApiModel.homeworld.name shouldBe "Tatooine"
        expandedSpeciesApiModel.films.shouldNotBeEmpty()
        expandedSpeciesApiModel.people.shouldNotBeEmpty()
    }

    @Test
    fun `getExpandedPlanetByUrl should return an ExpandedPlanetApiModel`() = runTest {
        val expandedPlanetApiModel =
            client.getExpandedPlanetByUrl("https://swapi.dev/api/planets/1/")

        expandedPlanetApiModel.original.name shouldBe "Tatooine"
        expandedPlanetApiModel.films.shouldNotBeEmpty()
        expandedPlanetApiModel.residents.shouldNotBeEmpty()
    }

    private fun buildMockEngine() = MockEngine { request ->
        when {
            request.url.toString().contains("people") -> {
                respond(
                    content = TestDataProvider.peopleJson,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            request.url.toString().contains("films") -> {
                respond(
                    content = TestDataProvider.filmJson,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            request.url.toString().contains("starships") -> {
                respond(
                    content = TestDataProvider.starshipJson,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            request.url.toString().contains("vehicles") -> {
                respond(
                    content = TestDataProvider.vehicleJson,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            request.url.toString().contains("species") -> {
                respond(
                    content = TestDataProvider.speciesJson,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            request.url.toString().contains("planets") -> {
                respond(
                    content = TestDataProvider.planetJson,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            else -> respondBadRequest()
        }
    }
}