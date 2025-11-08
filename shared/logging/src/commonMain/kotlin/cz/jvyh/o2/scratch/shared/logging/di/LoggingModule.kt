package cz.jvyh.o2.scratch.shared.logging.di

import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.shared.logging.infrastructure.LOGGER_CLASS_NAMED_FACTORY
import cz.jvyh.o2.scratch.shared.logging.infrastructure.LOGGER_SIMPLE_NAMED_FACTORY
import cz.jvyh.o2.scratch.shared.logging.infrastructure.simpleNameOrUnknown
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loggingModule = module {
    single<Logger> { Logger }
    factory<Logger>(qualifier = named(LOGGER_CLASS_NAMED_FACTORY)) { (classForTag: Any) ->
        get<Logger>().withTag(classForTag::class.simpleNameOrUnknown)
    }
    factory<Logger>(qualifier = named(LOGGER_SIMPLE_NAMED_FACTORY)) { (tag: String) -> get<Logger>().withTag(tag) }
}