package cz.jvyh.o2.scratch.shared.networking.ui.preview

import io.ktor.client.HttpClient
import org.koin.dsl.module

val composePreviewNetworkingModule = module {
    single<HttpClient> { HttpClient {} }
}