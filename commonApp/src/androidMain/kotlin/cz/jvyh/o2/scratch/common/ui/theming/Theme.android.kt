package cz.jvyh.o2.scratch.common.ui.theming

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cz.jvyh.o2.scratch.shared.common.ui.theming.ContrastLevel

@Composable
actual fun PlatformAppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    contrastLevel: ContrastLevel,
    content: @Composable (() -> Unit)
) {
    val dynamicColorSchemeProvider: (@Composable () -> ColorScheme)? =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
        } else null

    CommonAppTheme(
        darkTheme = darkTheme,
        customColorSchemeProvider = dynamicColorSchemeProvider,
        contrastLevel = contrastLevel,
        content = content
    )
}