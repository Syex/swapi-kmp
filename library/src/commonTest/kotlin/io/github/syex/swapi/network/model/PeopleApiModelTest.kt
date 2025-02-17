package io.github.syex.swapi.network.model

import io.kotest.matchers.shouldBe
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import kotlin.test.Test

class PeopleApiModelTest {

    private val json = """
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

    @Test
    fun `should parse PeopleApiModel correctly`() {
        val peopleApiModel = Json.decodeFromString<PeopleApiModel>(json)

        peopleApiModel.birthYear shouldBe "19 BBY"
        peopleApiModel.eyeColor shouldBe "Blue"
        peopleApiModel.filmUrls shouldBe listOf("https://swapi.dev/api/films/1/")
        peopleApiModel.gender shouldBe "Male"
        peopleApiModel.hairColor shouldBe "Blond"
        peopleApiModel.height shouldBe "172"
        peopleApiModel.homeworldUrl shouldBe "https://swapi.dev/api/planets/1/"
        peopleApiModel.mass shouldBe "77"
        peopleApiModel.name shouldBe "Luke Skywalker"
        peopleApiModel.skinColor shouldBe "Fair"
        peopleApiModel.created shouldBe Instant.parse("2014-12-09T13:50:51.644000Z")
        peopleApiModel.edited shouldBe Instant.parse("2014-12-10T13:52:43.172000Z")
        peopleApiModel.speciesUrls shouldBe listOf("https://swapi.dev/api/species/1/")
        peopleApiModel.starshipUrls shouldBe listOf("https://swapi.dev/api/starships/12/")
        peopleApiModel.url shouldBe "https://swapi.dev/api/people/1/"
        peopleApiModel.vehicleUrls shouldBe listOf("https://swapi.dev/api/vehicles/14/")
    }
}