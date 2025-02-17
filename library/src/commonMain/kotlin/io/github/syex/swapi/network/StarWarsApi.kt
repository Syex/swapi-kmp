package io.github.syex.swapi.network

import io.ktor.client.HttpClient

internal class StarWarsApi(private val httpClient: HttpClient) {

    companion object {
        private val BASE_API = "https://swapi.dev/api/"
    }
}