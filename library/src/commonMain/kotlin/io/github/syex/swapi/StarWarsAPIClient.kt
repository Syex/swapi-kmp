package io.github.syex.swapi

import io.github.syex.swapi.network.createDefaultHttpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

@Suppress("unused")
class StarWarsAPIClient internal constructor(
    private val httpClient: HttpClient,
) {

    companion object Builder {
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
