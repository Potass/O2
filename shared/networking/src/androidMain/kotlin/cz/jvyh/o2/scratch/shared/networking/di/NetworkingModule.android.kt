package cz.jvyh.o2.scratch.shared.networking.di

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.Logger

actual val httpNetworkEngineFactory: HttpClientEngineFactory<*> = OkHttp
actual val networkLogger: Logger = Logger.ANDROID