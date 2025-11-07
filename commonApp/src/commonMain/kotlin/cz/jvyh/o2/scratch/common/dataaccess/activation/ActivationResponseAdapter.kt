package cz.jvyh.o2.scratch.common.dataaccess.activation

import cz.jvyh.o2.scratch.common.domain.activation.ActivationResponse

internal class ActivationResponseAdapter {
    fun toDomain(dto: ActivationResponseDto) = ActivationResponse((dto.stateAsNumber ?: 0) >= ACTIVATED_THRESHOLD)

    private companion object {
        const val ACTIVATED_THRESHOLD = 277029
    }
}