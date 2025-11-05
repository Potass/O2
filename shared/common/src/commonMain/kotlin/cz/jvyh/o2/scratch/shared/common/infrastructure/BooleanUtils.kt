package cz.jvyh.o2.scratch.shared.common.infrastructure

fun Boolean?.toLong() = if (this == true) 1L else 0L

fun Boolean?.orFalse() = this == true
fun Boolean?.orTrue() = this != false
fun Boolean?.isFalse() = this == false
fun Boolean?.isTrue() = this == true

fun Boolean.whenTrue(action: () -> Unit) = run { if (this) action() }
fun <T : Any, R : Any> T.whenTrue(condition: Boolean, action: T.() -> R): R? = if (condition) action() else null
fun Boolean.whenFalse(action: () -> Unit) = run { if (not()) action() }
fun <R> Boolean.whenCondition(onTrue: () -> R, onFalse: () -> R) = if (this) onTrue() else onFalse()

fun <T> consumeAndTrue(action: () -> T): Boolean = run { action(); true }
fun <T> consumeAndFalse(action: () -> T): Boolean = run { action(); false }
