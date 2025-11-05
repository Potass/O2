package cz.jvyh.o2.scratch.shared.common.infrastructure

import androidx.compose.ui.graphics.Color

fun Boolean?.orDefault(default: Boolean = BooleanDefaults.DEFAULT_VALUE) = this ?: default
fun Boolean?.isNullOrDefault(default: Boolean = BooleanDefaults.DEFAULT_VALUE) = this == null || this == default
fun Boolean?.isNotNullOrDefault(default: Boolean = BooleanDefaults.DEFAULT_VALUE) = isNullOrDefault(default).not()
fun Long?.orDefault(default: Long = LongDefaults.DEFAULT_VALUE) = this ?: default
fun Long?.isNullOrDefault(default: Long = LongDefaults.DEFAULT_VALUE) = this == null || this == default
fun Long?.isNotNullOrDefault(default: Long = LongDefaults.DEFAULT_VALUE) = isNullOrDefault(default).not()
fun Double?.orDefault(default: Double = DoubleDefaults.DEFAULT_VALUE) = this ?: default
fun Double?.isNullOrDefault(default: Double = DoubleDefaults.DEFAULT_VALUE) = this == null || this == default
fun Double?.isNotNullOrDefault(default: Double = DoubleDefaults.DEFAULT_VALUE) = isNullOrDefault(default).not()
fun String?.orDefault(default: String = StringDefaults.DEFAULT_VALUE) = this ?: default
fun String?.isNullOrBlankOrDefault(default: String = StringDefaults.DEFAULT_VALUE) = isNullOrBlank() || this == default
fun String?.isNotNullOrBlankOrDefault(default: String = StringDefaults.DEFAULT_VALUE) = isNullOrBlankOrDefault(default).not()
fun String?.toDoubleOrDefault(default: Double = DoubleDefaults.DEFAULT_VALUE) = this?.toDoubleOrNull().orDefault(default)
fun Color?.orDefault(default: Color = ColorDefaults.DEFAULT_VALUE) = this ?: default

object BooleanDefaults {
    const val DEFAULT_VALUE = false
}

object IntDefaults {
    const val DEFAULT_VALUE = 0
}

object LongDefaults {
    const val DEFAULT_VALUE = 0L
}

object FloatDefaults {
    const val DEFAULT_VALUE = 0f
}

object DoubleDefaults {
    const val DEFAULT_VALUE = 0.0
}

object StringDefaults {
    const val DEFAULT_VALUE = EMPTY_STRING
}

object ColorDefaults {
    // Using max alpha channel to ensure the color is fully opaque.
    val DEFAULT_VALUE = Color(0xFF000000)
}