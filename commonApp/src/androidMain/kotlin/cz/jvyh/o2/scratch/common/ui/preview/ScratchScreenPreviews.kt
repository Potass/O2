package cz.jvyh.o2.scratch.common.ui.preview

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.jvyh.o2.scratch.common.ui.scratch.ScratchScreen
import cz.jvyh.o2.scratch.common.ui.theming.PlatformAppTheme
import cz.jvyh.o2.scratch.shared.common.ui.preview.KoinAwarePreview
import cz.jvyh.o2.scratch.shared.common.ui.theming.ContrastLevel

@Preview
@Composable
fun BaseScratchScreenPreview() {
    Surface {
        KoinAwarePreview(composePreviewAppPlatformModule) {
            ScratchScreen()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ScratchScreenScreenDayStandardContrastPreview() {
    PlatformAppTheme(dynamicColor = false) {
        BaseScratchScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ScratchScreenScreenDayMediumContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.Medium
    ) {
        BaseScratchScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ScratchScreenScreenDayHighContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.High
    ) {
        BaseScratchScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ScratchScreenScreenDynamicDayPreview() {
    PlatformAppTheme {
        BaseScratchScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScratchScreenScreenNightStandardContrastPreview() {
    PlatformAppTheme(dynamicColor = false) {
        BaseScratchScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScratchScreenScreenNightMediumContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.Medium
    ) {
        BaseScratchScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScratchScreenScreenNightHighContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.High
    ) {
        BaseScratchScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScratchScreenScreenDynamicNightPreview() {
    PlatformAppTheme {
        BaseScratchScreenPreview()
    }
}