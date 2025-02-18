package io.github.syex.swapi

import io.github.syex.swapi.network.createDefaultHttpClientEngine
import io.github.syex.swapi.network.model.PagedResponse
import io.github.syex.swapi.network.model.PeopleApiModel
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

    suspend fun getAllPeople(): PagedResponse<PeopleApiModel> {
        return httpClient.get("$BASE_API/people").body()
    }

    suspend fun getPeopleByUrl(url: String): PeopleApiModel {
        return httpClient.get(url).body()
    }

    suspend fun <T> loadNextPage(pageUrl: String): PagedResponse<T> {
        return httpClient.get(pageUrl).body()
    }

    companion object Builder {
        private val BASE_API = "https://swapi.dev/api"

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
