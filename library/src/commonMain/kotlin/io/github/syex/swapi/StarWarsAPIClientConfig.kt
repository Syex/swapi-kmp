package io.github.syex.swapi

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import kotlinx.serialization.json.Json

/**
 * Configuration for the Star Wars API client.
 *
 * This class allows you to customize various aspects of the HTTP client used by the library,
 * such as timeouts, serialization settings, and additional configuration options.
 *
 * @param httpClientEngine Used to pass a custom HTTP client engine.
 * @param connectTimeoutMs The timeout for establishing a connection in milliseconds.
 * @param requestTimeoutMs The timeout for sending a request in milliseconds.
 * @param socketTimeoutMs The timeout for reading data from the server in milliseconds.
 * @param jsonConfig The serialization settings for JSON data.
 * @param additionalConfig A function to customize the Ktor HTTP client further.
 */
data class StarWarsAPIClientConfig(
    var httpClientEngine: HttpClientEngine? = null,
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