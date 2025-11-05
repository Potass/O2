package cz.jvyh.o2.scratch.common.ui.theming

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import cz.jvyh.o2.scratch.common.Oxanium_Bold
import cz.jvyh.o2.scratch.common.Oxanium_ExtraBold
import cz.jvyh.o2.scratch.common.Oxanium_ExtraLight
import cz.jvyh.o2.scratch.common.Oxanium_Light
import cz.jvyh.o2.scratch.common.Oxanium_Medium
import cz.jvyh.o2.scratch.common.Oxanium_Regular
import cz.jvyh.o2.scratch.common.Oxanium_SemiBold
import cz.jvyh.o2.scratch.common.Res
import org.jetbrains.compose.resources.Font

@get:Composable
val oxaniumFontFamily: FontFamily
    get() = FontFamily(
        Font(resource = Res.font.Oxanium_Regular, weight = FontWeight.Normal, style = FontStyle.Normal),
        Font(resource = Res.font.Oxanium_Light, weight = FontWeight.Light, style = FontStyle.Normal),
        Font(resource = Res.font.Oxanium_ExtraLight, weight = FontWeight.ExtraLight, style = FontStyle.Normal),
        Font(resource = Res.font.Oxanium_Medium, weight = FontWeight.Medium, style = FontStyle.Normal),
        Font(resource = Res.font.Oxanium_Bold, weight = FontWeight.Bold, style = FontStyle.Normal),
        Font(resource = Res.font.Oxanium_SemiBold, weight = FontWeight.SemiBold, style = FontStyle.Normal),
        Font(resource = Res.font.Oxanium_ExtraBold, weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    )

@get:Composable
private val bodyFontFamily: FontFamily get() = oxaniumFontFamily

@get:Composable
private val displayFontFamily: FontFamily get() = oxaniumFontFamily

// Default Material 3 typography values
private val baseline = Typography()

@get:Composable
val appTypography: Typography
    get() = Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
    )