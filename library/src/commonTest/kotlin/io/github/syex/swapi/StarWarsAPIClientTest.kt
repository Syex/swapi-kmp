package io.github.syex.swapi

import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import kotlin.test.Test

class StarWarsAPIClientTest {

    @Test
    fun `getAllPeople should return a paged people response`() = runTest {
        val client = StarWarsAPIClient.create {
            httpClientEngine = buildMockEngine(
                expectedRequestUrl = "https://swapi.dev/api/people",
                contentBuilder = { buildPagedJsonResponse(peopleJson) }
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
                contentBuilder = { peopleJson }
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
        model.starshipUrls shouldBe listOf("https://swapi.dev/api/starships/12/")
        model.url shouldBe "https://swapi.dev/api/people/1/"
        model.vehicleUrls shouldBe listOf("https://swapi.dev/api/vehicles/14/")
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

    private val peopleJson = """
        {
            "birth_year": "19 BBY",
            "eye_color": "Blue",
            "films": [
                "https://swapi.dev/api/films/1/"
            ],
            "gender": "Male",
            "hair_color": "Blond",
            "height": "172",
            "homeworld": "https://swapi.dev/api/planets/1/",
            "mass": "77",
            "name": "Luke Skywalker",
            "skin_color": "Fair",
            "created": "2014-12-09T13:50:51.644000Z",
            "edited": "2014-12-10T13:52:43.172000Z",
            "species": [
                "https://swapi.dev/api/species/1/"
            ],
            "starships": [
                "https://swapi.dev/api/starships/12/"
            ],
            "url": "https://swapi.dev/api/people/1/",
            "vehicles": [
                "https://swapi.dev/api/vehicles/14/"
            ]
        }
    """.trimIndent()
}