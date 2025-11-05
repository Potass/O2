package cz.jvyh.o2.scratch.shared.logging.infrastructure

import co.touchlab.kermit.Logger
import kotlin.reflect.KClass

val <T: Any> KClass<T>.simpleNameOrUnknown: String; get() = simpleName ?: "UNKNOWN_CLASS_NAME"

inline fun <reified T : Any> Logger.withClassNameTag() = withTag(T::class.simpleNameOrUnknown)
