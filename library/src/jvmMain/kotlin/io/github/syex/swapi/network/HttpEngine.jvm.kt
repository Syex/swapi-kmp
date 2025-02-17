package io.github.syex.swapi.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun createDefaultHttpClientEngine(): HttpClientEngine = OkHttp.create()