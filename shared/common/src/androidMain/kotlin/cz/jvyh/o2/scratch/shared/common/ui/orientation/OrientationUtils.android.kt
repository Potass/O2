package cz.jvyh.o2.scratch.shared.common.ui.orientation

import android.content.res.Configuration

fun Int.isPortrait() = this == Configuration.ORIENTATION_PORTRAIT
fun Int.isLandscape() = this == Configuration.ORIENTATION_LANDSCAPE
fun Int.isUndefined() = this == Configuration.ORIENTATION_UNDEFINED