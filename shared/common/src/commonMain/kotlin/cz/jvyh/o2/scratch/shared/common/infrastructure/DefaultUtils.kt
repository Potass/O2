@file:Suppress("KotlinConstantConditions")

package cz.jvyh.o2.scratch.shared.common.infrastructure

fun Boolean?.orDefault(default: Boolean = BooleanDefaults.DEFAULT_VALUE) = this ?: default
fun Boolean?.isNullOrDefault(default: Boolean = BooleanDefaults.DEFAULT_VALUE) = this == null || this == default
fun Boolean?.isNotNullOrDefault(default: Boolean = BooleanDefaults.DEFAULT_VALUE) = isNullOrDefault(default).not()
fun String?.orDefault(default: String = StringDefaults.DEFAULT_VALUE) = this ?: default
fun String?.isNullOrBlankOrDefault(default: String = StringDefaults.DEFAULT_VALUE) = isNullOrBlank() || this == default
fun String?.isNotNullOrBlankOrDefault(default: String = StringDefaults.DEFAULT_VALUE) = isNullOrBlankOrDefault(default).not()

object BooleanDefaults {
    const val DEFAULT_VALUE = false
}

object StringDefaults {
    const val DEFAULT_VALUE = EMPTY_STRING
}