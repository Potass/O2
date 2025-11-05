package cz.jvyh.o2.scratch.shared.common.domain

sealed interface CommonImageVectorIconKey : ImageVectorIconKey {
    data object AutoMirroredFilledArrowBackImageVectorIcon : CommonImageVectorIconKey
}