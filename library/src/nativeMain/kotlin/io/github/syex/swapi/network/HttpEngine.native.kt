package io.github.syex.swapi.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun createDefaultHttpClientEngine(): HttpClientEngine = Darwin.create()