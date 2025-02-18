package io.github.syex.swapi.network.model

import kotlinx.serialization.Serializable

@Serializable
class PagedResponse<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>,
)
