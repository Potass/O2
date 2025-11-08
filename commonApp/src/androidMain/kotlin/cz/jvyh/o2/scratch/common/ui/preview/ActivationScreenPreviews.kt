package cz.jvyh.o2.scratch.common.ui.preview

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.jvyh.o2.scratch.common.ui.activation.ActivationScreen
import cz.jvyh.o2.scratch.common.ui.theming.PlatformAppTheme
import cz.jvyh.o2.scratch.shared.common.ui.preview.KoinAwarePreview
import cz.jvyh.o2.scratch.shared.common.ui.theming.ContrastLevel

@Preview
@Composable
fun BaseActivationScreenPreview() {
    Surface {
        KoinAwarePreview(composePreviewAppPlatformModule) {
            ActivationScreen()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ActivationScreenScreenDayStandardContrastPreview() {
    PlatformAppTheme(dynamicColor = false) {
        BaseActivationScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ActivationScreenScreenDayMediumContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.Medium
    ) {
        BaseActivationScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ActivationScreenScreenDayHighContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.High
    ) {
        BaseActivationScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ActivationScreenScreenDynamicDayPreview() {
    PlatformAppTheme {
        BaseActivationScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActivationScreenScreenNightStandardContrastPreview() {
    PlatformAppTheme(dynamicColor = false) {
        BaseActivationScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActivationScreenScreenNightMediumContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.Medium
    ) {
        BaseActivationScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActivationScreenScreenNightHighContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.High
    ) {
        BaseActivationScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActivationScreenScreenDynamicNightPreview() {
    PlatformAppTheme {
        BaseActivationScreenPreview()
    }
}