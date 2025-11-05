package cz.jvyh.o2.scratch.shared.logging.di

import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.shared.logging.infrastructure.LOGGER_CLASS_NAMED_FACTORY
import cz.jvyh.o2.scratch.shared.logging.infrastructure.LOGGER_SIMPLE_NAMED_FACTORY
import cz.jvyh.o2.scratch.shared.logging.infrastructure.simpleNameOrUnknown
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loggingModule = module {
    // Base singleton logger with default configuration (not specified right now).
    // It is necessary to specify type explicitly otherwise it will crash in conjunction with factory below which also must do the same.
    single<Logger> { Logger }
    // Must be named to be able to also inject base singleton above.
    factory<Logger>(qualifier = named(LOGGER_CLASS_NAMED_FACTORY)) { (classForTag: Any) ->
        get<Logger>().withTag(classForTag::class.simpleNameOrUnknown)
    }
    factory<Logger>(qualifier = named(LOGGER_SIMPLE_NAMED_FACTORY)) { (tag: String) -> get<Logger>().withTag(tag) }
}