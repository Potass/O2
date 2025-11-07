package cz.jvyh.o2.scratch.common.dataaccess.activation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ActivationResponseDto(
    @SerialName("android") val stateAsNumber: Int? = null,
)
