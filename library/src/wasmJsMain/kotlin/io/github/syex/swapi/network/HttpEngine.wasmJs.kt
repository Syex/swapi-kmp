package io.github.syex.swapi.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.JsClient

actual fun createDefaultHttpClientEngine(): HttpClientEngine = JsClient().create()