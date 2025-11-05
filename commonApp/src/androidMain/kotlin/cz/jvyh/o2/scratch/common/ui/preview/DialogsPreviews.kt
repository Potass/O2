package cz.jvyh.o2.scratch.common.ui.preview

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.jvyh.o2.scratch.common.ui.theming.PlatformAppTheme
import cz.jvyh.o2.scratch.shared.common.ui.preview.FullAppAlertDialogPreview
import cz.jvyh.o2.scratch.shared.common.ui.theming.ContrastLevel

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AppAlertDialogDayStandardContrastPreview() {
    PlatformAppTheme(dynamicColor = false) {
        FullAppAlertDialogPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AppAlertDialogDayMediumContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.Medium
    ) {
        FullAppAlertDialogPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AppAlertDialogDayHighContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.High
    ) {
        FullAppAlertDialogPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AppAlertDialogDynamicDayPreview() {
    PlatformAppTheme {
        FullAppAlertDialogPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppAlertDialogNightStandardContrastPreview() {
    PlatformAppTheme(dynamicColor = false) {
        FullAppAlertDialogPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppAlertDialogNightMediumContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.Medium
    ) {
        FullAppAlertDialogPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppAlertDialogNightHighContrastPreview() {
    PlatformAppTheme(
        dynamicColor = false,
        contrastLevel = ContrastLevel.High
    ) {
        FullAppAlertDialogPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppAlertDialogDynamicNightPreview() {
    PlatformAppTheme {
        FullAppAlertDialogPreview()
    }
}