package cz.jvyh.o2.scratch.shared.common.ui.constants

import androidx.compose.ui.unit.dp

object BasicDimens {
    val Unit0 = 0.dp
    val Unit0_1_16 = 1.dp
    val Unit0_1_8 = 2.dp
    val Unit0_1_4 = 4.dp
    val Unit0_1_2 = 8.dp
    val Unit1 = 16.dp
    val Unit2 = Unit1 * 2
    val Unit3 = Unit1 * 3
}

object SpacingDimens {
    val None = BasicDimens.Unit0
    val Default3XS = BasicDimens.Unit0_1_16
    val Default2XS = BasicDimens.Unit0_1_8
    val DefaultXS = BasicDimens.Unit0_1_4
    val DefaultS = BasicDimens.Unit0_1_2
    val Default = BasicDimens.Unit1
    val Default2 = BasicDimens.Unit2
}