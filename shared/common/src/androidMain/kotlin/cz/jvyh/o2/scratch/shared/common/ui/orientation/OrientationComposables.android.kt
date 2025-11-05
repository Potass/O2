package cz.jvyh.o2.scratch.shared.common.ui.orientation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun WithOrientation(
    defaultContent: @Composable () -> Unit,
    otherContent: @Composable () -> Unit,
) {
    val configuration = LocalConfiguration.current
    var orientation by remember { mutableIntStateOf(configuration.orientation) }

    if (orientation.isPortrait()) defaultContent() else otherContent()
}