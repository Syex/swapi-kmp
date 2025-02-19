package io.github.syex.swapi

object TestDataProvider {
    val peopleJson = """
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
                "https://swapi.dev/api/starships/1/"
            ],
            "url": "https://swapi.dev/api/people/1/",
            "vehicles": [
                "https://swapi.dev/api/vehicles/1/"
            ]
        }
    """.trimIndent()

    val filmJson = """
        {
            "characters": [
                "https://swapi.dev/api/people/1/"
            ],
            "created": "2014-12-10T14:23:31.880000Z",
            "director": "George Lucas",
            "edited": "2014-12-12T11:24:39.858000Z",
            "episode_id": 4,
            "opening_crawl": "It is a period of civil war...",
            "planets": [
                "https://swapi.dev/api/planets/1/"
            ],
            "producer": "Gary Kurtz, Rick McCallum",
            "release_date": "1977-05-25",
            "species": [
                "https://swapi.dev/api/species/1/"
            ],
            "starships": [
                "https://swapi.dev/api/starships/1/"
            ],
            "title": "A New Hope",
            "url": "https://swapi.dev/api/films/1/",
            "vehicles": [
                "https://swapi.dev/api/vehicles/1/"
            ]
        }
    """.trimIndent()

    val starshipJson = """
        {
            "MGLT": "10 MGLT",
            "cargo_capacity": "1000000000000",
            "consumables": "3 years",
            "cost_in_credits": "1000000000000",
            "created": "2014-12-10T16:36:50.509000Z",
            "crew": "342953",
            "edited": "2014-12-10T16:36:50.509000Z",
            "hyperdrive_rating": "4.0",
            "length": "120000",
            "manufacturer": "Imperial Department of Military Research, Sienar Fleet Systems",
            "max_atmosphering_speed": "n/a",
            "model": "DS-1 Orbital Battle Station",
            "name": "Death Star",
            "passengers": "843342",
            "films": [
                "https://swapi.dev/api/films/1/"
            ],
            "pilots": [
                "https://swapi.dev/api/people/1/"
            ],
            "starship_class": "Deep Space Mobile Battlestation",
            "url": "https://swapi.dev/api/starships/1/"
        }
    """.trimIndent()

    val vehicleJson = """
        {
            "cargo_capacity": "50000",
            "consumables": "2 months",
            "cost_in_credits": "150000",
            "created": "2014-12-10T15:36:25.724000Z",
            "crew": "46",
            "edited": "2014-12-10T15:36:25.724000Z",
            "length": "36.8",
            "manufacturer": "Corellia Mining Corporation",
            "max_atmosphering_speed": "30",
            "model": "Digger Crawler",
            "name": "Sand Crawler",
            "passengers": "30",
            "pilots": [
                "https://swapi.dev/api/people/1/"
            ],
            "films": [
                "https://swapi.dev/api/films/1/"
            ],
            "url": "https://swapi.dev/api/vehicles/1/",
            "vehicle_class": "wheeled"
        }
    """.trimIndent()

    val speciesJson = """
        {
            "average_height": "2.1",
            "average_lifespan": "400",
            "classification": "Mammal",
            "created": "2014-12-10T16:44:31.486000Z",
            "designation": "Sentient",
            "edited": "2014-12-10T16:44:31.486000Z",
            "eye_colors": "blue, green, yellow, brown, golden, red",
            "hair_colors": "black, brown",
            "homeworld": "https://swapi.dev/api/planets/1/",
            "language": "Shyriiwook",
            "name": "Wookie",
            "people": [
                "https://swapi.dev/api/people/1/"
            ],
            "films": [
                "https://swapi.dev/api/films/1/"
            ],
            "skin_colors": "gray",
            "url": "https://swapi.dev/api/species/1/"
        }
    """.trimIndent()

    val planetJson = """
        {
            "climate": "Arid",
            "created": "2014-12-09T13:50:49.641000Z",
            "diameter": "10465",
            "edited": "2014-12-15T13:48:16.167217Z",
            "films": [
                "https://swapi.dev/api/films/1/"
            ],
            "gravity": "1",
            "name": "Tatooine",
            "orbital_period": "304",
            "population": "120000",
            "residents": [
                "https://swapi.dev/api/people/1/"
            ],
            "rotation_period": "23",
            "surface_water": "1",
            "terrain": "Dessert",
            "url": "https://swapi.dev/api/planets/1/"
        }
    """.trimIndent()
}