package cz.jvyh.o2.scratch.shared.networking.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkingModule = module {
    singleOf(::provideHttpClient)
}

private fun provideHttpClient() = HttpClient(httpNetworkEngineFactory) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true // Helpful for APIs that might have inconsistencies.
            ignoreUnknownKeys = true // Crucial for APIs that might add new fields.
            coerceInputValues = true // Tries to coerce incorrect types (e.g. "10" to 10.0).
        })
    }
    install(Logging) {
        logger = networkLogger
        level = LogLevel.ALL
    }
}

expect val httpNetworkEngineFactory: HttpClientEngineFactory<*>
expect val networkLogger: Logger