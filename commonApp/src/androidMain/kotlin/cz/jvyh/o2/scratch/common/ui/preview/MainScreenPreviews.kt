package cz.jvyh.o2.scratch.common.ui.preview

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.jvyh.o2.scratch.common.ui.main.MainScreen
import cz.jvyh.o2.scratch.common.ui.theming.PlatformAppTheme
import cz.jvyh.o2.scratch.shared.common.ui.preview.KoinAwarePreview
import cz.jvyh.o2.scratch.shared.common.ui.theming.ContrastLevel

@Preview
@Composable
fun BaseMainScreenPreview() {
    Surface {
        KoinAwarePreview(composePreviewAppModule) {
            MainScreen({}, {})
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MainScreenScreenDayStandardContrastPreview() {
    PlatformAppTheme(dynamicColor = false) {
        BaseMainScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MainScreenScreenDayMediumContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.Medium
    ) {
        BaseMainScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MainScreenScreenDayHighContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.High
    ) {
        BaseMainScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MainScreenScreenDynamicDayPreview() {
    PlatformAppTheme {
        BaseMainScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenScreenNightStandardContrastPreview() {
    PlatformAppTheme(dynamicColor = false) {
        BaseMainScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenScreenNightMediumContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.Medium
    ) {
        BaseMainScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenScreenNightHighContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.High
    ) {
        BaseMainScreenPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenScreenDynamicNightPreview() {
    PlatformAppTheme {
        BaseMainScreenPreview()
    }
}