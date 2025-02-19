package io.github.syex.swapi

import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlin.test.Test

class StarWarsAPIClientTest {

    @Test
    fun `getAllPeople should return a paged people response`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/people",
                contentBuilder = { buildPagedJsonResponse(TestDataProvider.peopleJson) }
            )
        }

        val pagedResponse = client.getAllPeople()

        pagedResponse.results.size shouldBe 1
        pagedResponse.results.first().name shouldBe "Luke Skywalker"
    }

    @Test
    fun `getPeopleByUrl should return a people api model`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/people/1",
                contentBuilder = { TestDataProvider.peopleJson }
            )
        }

        val model = client.getPeopleByUrl("https://swapi.dev/api/people/1")

        model.birthYear shouldBe "19 BBY"
        model.eyeColor shouldBe "Blue"
        model.filmUrls shouldBe listOf("https://swapi.dev/api/films/1/")
        model.gender shouldBe "Male"
        model.hairColor shouldBe "Blond"
        model.height shouldBe "172"
        model.homeworldUrl shouldBe "https://swapi.dev/api/planets/1/"
        model.mass shouldBe "77"
        model.name shouldBe "Luke Skywalker"
        model.skinColor shouldBe "Fair"
        model.created shouldBe Instant.parse("2014-12-09T13:50:51.644000Z")
        model.edited shouldBe Instant.parse("2014-12-10T13:52:43.172000Z")
        model.speciesUrls shouldBe listOf("https://swapi.dev/api/species/1/")
        model.starshipUrls shouldBe listOf("https://swapi.dev/api/starships/1/")
        model.url shouldBe "https://swapi.dev/api/people/1/"
        model.vehicleUrls shouldBe listOf("https://swapi.dev/api/vehicles/1/")
    }

    @Test
    fun `getAllFilms should return a paged film response`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/films",
                contentBuilder = { buildPagedJsonResponse(TestDataProvider.filmJson) }
            )
        }

        val pagedResponse = client.getAllFilms()

        pagedResponse.results.size shouldBe 1
        pagedResponse.results.first().title shouldBe "A New Hope"
    }

    @Test
    fun `getFilmByUrl should return a film api model`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/films/1",
                contentBuilder = { TestDataProvider.filmJson }
            )
        }

        val model = client.getFilmByUrl("https://swapi.dev/api/films/1")

        model.characters shouldBe listOf("https://swapi.dev/api/people/1/")
        model.created shouldBe Instant.parse("2014-12-10T14:23:31.880000Z")
        model.director shouldBe "George Lucas"
        model.edited shouldBe Instant.parse("2014-12-12T11:24:39.858000Z")
        model.episodeId shouldBe 4
        model.openingCrawl.shouldNotBeEmpty()
        model.planetUrls shouldBe listOf("https://swapi.dev/api/planets/1/")
        model.producer shouldBe "Gary Kurtz, Rick McCallum"
        model.releaseDate shouldBe LocalDate.parse("1977-05-25")
        model.speciesUrls shouldBe listOf("https://swapi.dev/api/species/1/")
        model.starshipUrls shouldBe listOf("https://swapi.dev/api/starships/1/")
        model.title shouldBe "A New Hope"
        model.url shouldBe "https://swapi.dev/api/films/1/"
        model.vehicleUrls shouldBe listOf("https://swapi.dev/api/vehicles/1/")
    }

    @Test
    fun `getAllStarships should return a paged starship response`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/starships",
                contentBuilder = { buildPagedJsonResponse(TestDataProvider.starshipJson) }
            )
        }

        val pagedResponse = client.getAllStarships()

        pagedResponse.results.size shouldBe 1
        pagedResponse.results.first().name shouldBe "Death Star"
    }

    @Test
    fun `getStarshipByUrl should return a starship api model`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/starships/1",
                contentBuilder = { TestDataProvider.starshipJson }
            )
        }

        val model = client.getStarshipByUrl("https://swapi.dev/api/starships/1")

        model.mglt shouldBe "10 MGLT"
        model.cargoCapacity shouldBe "1000000000000"
        model.consumables shouldBe "3 years"
        model.costInCredits shouldBe "1000000000000"
        model.created shouldBe Instant.parse("2014-12-10T16:36:50.509000Z")
        model.crew shouldBe "342953"
        model.edited shouldBe Instant.parse("2014-12-10T16:36:50.509000Z")
        model.hyperdriveRating shouldBe "4.0"
        model.length shouldBe "120000"
        model.manufacturer shouldBe "Imperial Department of Military Research, Sienar Fleet Systems"
        model.maxAtmospheringSpeed shouldBe "n/a"
        model.model shouldBe "DS-1 Orbital Battle Station"
        model.name shouldBe "Death Star"
        model.passengers shouldBe "843342"
        model.filmUrls.shouldNotBeEmpty()
        model.pilotUrls.shouldNotBeEmpty()
        model.starshipClass shouldBe "Deep Space Mobile Battlestation"
        model.url shouldBe "https://swapi.dev/api/starships/1/"
    }

    @Test
    fun `getAllVehicles should return a paged vehicle response`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/vehicles",
                contentBuilder = { buildPagedJsonResponse(TestDataProvider.vehicleJson) }
            )
        }

        val pagedResponse = client.getAllVehicles()

        pagedResponse.results.size shouldBe 1
        pagedResponse.results.first().name shouldBe "Sand Crawler"
    }

    @Test
    fun `getVehicleByUrl should return a vehicle model`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/vehicles/1",
                contentBuilder = { TestDataProvider.vehicleJson }
            )
        }

        val model = client.getVehicleByUrl("https://swapi.dev/api/vehicles/1")

        model.cargoCapacity shouldBe "50000"
        model.consumables shouldBe "2 months"
        model.costInCredits shouldBe "150000"
        model.created shouldBe Instant.parse("2014-12-10T15:36:25.724000Z")
        model.crew shouldBe "46"
        model.edited shouldBe Instant.parse("2014-12-10T15:36:25.724000Z")
        model.length shouldBe "36.8"
        model.manufacturer shouldBe "Corellia Mining Corporation"
        model.maxAtmospheringSpeed shouldBe "30"
        model.model shouldBe "Digger Crawler"
        model.name shouldBe "Sand Crawler"
        model.passengers shouldBe "30"
        model.pilotUrls.shouldNotBeEmpty()
        model.filmUrls.shouldNotBeEmpty()
        model.url shouldBe "https://swapi.dev/api/vehicles/1/"
        model.vehicleClass shouldBe "wheeled"
    }

    @Test
    fun `getAllSpecies should return a paged species response`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/species",
                contentBuilder = { buildPagedJsonResponse(TestDataProvider.speciesJson) }
            )
        }

        val pagedResponse = client.getAllSpecies()

        pagedResponse.results.size shouldBe 1
        pagedResponse.results.first().name shouldBe "Wookie"
    }

    @Test
    fun `getSpeciesByUrl should return a species api model`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/species/1",
                contentBuilder = { TestDataProvider.speciesJson }
            )
        }

        val model = client.getSpeciesByUrl("https://swapi.dev/api/species/1")

        model.averageHeight shouldBe "2.1"
        model.averageLifespan shouldBe "400"
        model.classification shouldBe "Mammal"
        model.created shouldBe Instant.parse("2014-12-10T16:44:31.486000Z")
        model.designation shouldBe "Sentient"
        model.edited shouldBe Instant.parse("2014-12-10T16:44:31.486000Z")
        model.eyeColors shouldBe "blue, green, yellow, brown, golden, red"
        model.hairColors shouldBe "black, brown"
        model.homeworldUrl shouldBe "https://swapi.dev/api/planets/1/"
        model.language shouldBe "Shyriiwook"
        model.name shouldBe "Wookie"
        model.peopleUrls.shouldNotBeEmpty()
        model.filmUrls.shouldNotBeEmpty()
        model.skinColors shouldBe "gray"
        model.url shouldBe "https://swapi.dev/api/species/1/"
    }

    @Test
    fun `getAllPlanets should return a paged planet response`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/planets",
                contentBuilder = { buildPagedJsonResponse(TestDataProvider.planetJson) }
            )
        }

        val pagedResponse = client.getAllPlanets()

        pagedResponse.results.size shouldBe 1
        pagedResponse.results.first().name shouldBe "Tatooine"
    }

    @Test
    fun `getPlanetByUrl should return a planet api model`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/planets/1",
                contentBuilder = { TestDataProvider.planetJson }
            )
        }

        val model = client.getPlanetByUrl("https://swapi.dev/api/planets/1")

        model.climate shouldBe "Arid"
        model.created shouldBe Instant.parse("2014-12-09T13:50:49.641000Z")
        model.diameter shouldBe "10465"
        model.edited shouldBe Instant.parse("2014-12-15T13:48:16.167217Z")
        model.filmUrls.shouldNotBeEmpty()
        model.gravity shouldBe "1"
        model.name shouldBe "Tatooine"
        model.orbitalPeriod shouldBe "304"
        model.population shouldBe "120000"
        model.residentUrls.shouldNotBeEmpty()
        model.rotationPeriod shouldBe "23"
        model.surfaceWater shouldBe "1"
        model.terrain shouldBe "Dessert"
        model.url shouldBe "https://swapi.dev/api/planets/1/"
    }

    private fun buildMockEngine(expectedRequestUrl: String, contentBuilder: () -> String) =
        MockEngine { request ->
            if (request.url.toString() == expectedRequestUrl) {
                respond(
                    content = contentBuilder(),
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else {
                respondBadRequest()
            }
        }

    private fun buildPagedJsonResponse(singleResultJson: String): String {
        return """
            {
            	"count": 1,
            	"next": "https://swapi.dev/api/people/?page=2",
            	"previous": null,
            	"results": [
            		$singleResultJson
            	]
            }
        """.trimIndent()
    }
}