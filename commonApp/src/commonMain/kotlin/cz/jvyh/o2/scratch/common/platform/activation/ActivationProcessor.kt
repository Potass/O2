package cz.jvyh.o2.scratch.common.platform.activation

import cz.jvyh.o2.scratch.common.domain.activation.ActivationRequest
import cz.jvyh.o2.scratch.common.domain.activation.ActivationResponse

internal interface ActivationProcessor {
    suspend fun activate(request: ActivationRequest): ActivationResponse?
}