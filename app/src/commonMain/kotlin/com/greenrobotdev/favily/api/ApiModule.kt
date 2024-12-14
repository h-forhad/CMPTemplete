package com.greenrobotdev.favily.api

import com.greenrobotdev.core.network.APIConstants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val appApiModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            prettyPrint = true
            coerceInputValues = true
        }
    }

    single {
        HttpClient {

            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }

            install(Logging) { logger = Logger.SIMPLE }

            defaultRequest {
                url {
                    host = APIConstants.BASE_URL
                    protocol = URLProtocol.HTTPS
                }
            }
        }
    }
}