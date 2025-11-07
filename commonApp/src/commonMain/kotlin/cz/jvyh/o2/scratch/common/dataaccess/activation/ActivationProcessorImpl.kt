package cz.jvyh.o2.scratch.common.dataaccess.activation

import cz.jvyh.o2.scratch.common.domain.activation.ActivationRequest
import cz.jvyh.o2.scratch.common.domain.activation.ActivationResponse
import cz.jvyh.o2.scratch.common.platform.activation.ActivationProcessor
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.path

internal class ActivationProcessorImpl(
    httpClient: HttpClient,
) : ActivationProcessor {
    private val client = httpClient.config {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = HOST
            }
        }
    }
    private val responseAdapter = ActivationResponseAdapter()

    override suspend fun activate(request: ActivationRequest): ActivationResponse? = runCatching {
        client.get {
            url {
                path(*PATH)
                parameter(CODE_PARAM_NAME, request.code)
            }
        }.body<ActivationResponseDto>().let(responseAdapter::toDomain)
    }.getOrNull()

    private companion object {
        const val HOST = "api.o2.sk"
        val PATH = arrayOf("version")
        const val CODE_PARAM_NAME = "code"
    }
}