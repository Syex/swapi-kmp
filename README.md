[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
![Maven Central Version](https://img.shields.io/maven-central/v/io.github.syex/swapi)
![example workflow](https://github.com/Syex/swapi-kmp/actions/workflows/gradle.yml/badge.svg)

# SWAPI KMP

This is a Kotlin Multiplatform library that wraps the [Star Wars API](https://swapi.dev/). It
provides
a simple and easy-to-use interface to interact with the SWAPI data.

The main purpose is to provide an easy way to show some data in a playground or PoC app, like
a Compose Multiplatform app.

----

**Supported platfroms**:

- Android
- iOS
- JVM

----

## Installation

```
repositories {
    mavenCentral()
}

// in your commonMain sourceset
dependencies {
    implementation("io.github.syex:swapi:$version")
}
```

## Usage

The main class is the `StarWarsApiClient`. You get an instance via `StarWarsApiClient.create()`.

```
val client = StarWarsApiClient.create()
```

The `create()` method optionally takes a lambda that has a `StarWarsAPIClientConfig` as its
receiver,
which allows you to tweak some settings of the Ktor client, which is used to make the network calls.
If you leave it the following defaults will be used:

```
data class StarWarsAPIClientConfig(
    var httpClientEngine: HttpClientEngine? = null, // uses platform default then
    var connectTimeoutMs: Long = 10_000,
    var requestTimeoutMs: Long = 10_000,
    var socketTimeoutMs: Long = 10_000,
    var jsonConfig: Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = true
    },
    var additionalConfig: HttpClientConfig<*>.() -> Unit = {},
)
```

e.g.

```
val client = StarWarsApiClient.create {
    connectTimeoutMs = 5_000L
}
```

### StarWarsApiClient

The [Star Wars API](https://swapi.dev/) provides six different kind of endpoints, which the
library maps as following:

- `/people/1` -> `PeopleApiModel`
- `/planets/1` -> `PlanetApiModel`
- `/films/1` -> `FilmApiModel`
- `/species/1` -> `SpeciesApiModel`
- `/starships/1` -> `StarshipsApiModel`
- `/vehicles/1` -> `VehicleApiModel`

The `StarWarsApiClient` provides the same set of methods for each endpoint, e.g. for `/starships`:

- `fun searchStarships(query: String): PagedResponse<StarshipApiModel>`
- `fun getAllStarships(pageUrl: String? = null): PagedResponse<StarshipApiModel>`
- `fun getStarshipByUrl(url: String): StarshipApiModel`
- `fun getExpandedStarshipByUrl(url: String): ExpandedStarshipApiModel`

The `getAll*()` methods take an optional page url, which should be the value of `PagedResponse.next`
if you want to load the next page of the initial response.

### Expanded models

The `StarWarsApiClient` has methods to get an _extended_ model next to the normal API response. This
is a concept this library introduced so it's easier to display responses in a UI.

The SWAPI contains any references, like the starships and films of a person, as hyperlinks. An
_extended_ model did an additional round of network requests to resolve all of these hyperlinks
once.

So instead of returning a `PeopleApiModel` with a list of starship and film URLs, you would get a
`ExpandedPeopleApiModel` which contains the same data plus the resolved `StarshipApiModels` and
`FilmApiModels`.
