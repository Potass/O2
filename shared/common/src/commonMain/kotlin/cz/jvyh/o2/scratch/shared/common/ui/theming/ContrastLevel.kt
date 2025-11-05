package cz.jvyh.o2.scratch.shared.common.ui.theming

enum class ContrastLevel {
    // Order matters!
    Standard, Medium, High;

    fun isHigh() = this == High
    fun isMedium() = this == Medium
}