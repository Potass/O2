package cz.jvyh.o2.scratch.shared.common.ui.preview

import cz.jvyh.o2.scratch.shared.common.infrastructure.DefaultDispatcherProvider
import cz.jvyh.o2.scratch.shared.common.infrastructure.DispatcherProvider
import cz.jvyh.o2.scratch.shared.common.platform.AppDestinationToNavigateControllerImpl
import cz.jvyh.o2.scratch.shared.common.platform.AppDestinationToNavigateProvider
import cz.jvyh.o2.scratch.shared.common.platform.AppDestinationToNavigateUpdater
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorControllerImpl
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorInvalidator
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorVisibilityProvider
import cz.jvyh.o2.scratch.shared.common.platform.DialogToShowControllerImpl
import cz.jvyh.o2.scratch.shared.common.platform.DialogToShowProvider
import cz.jvyh.o2.scratch.shared.common.platform.DialogToShowUpdater
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppDrawableResourceIconProvider
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppImageVectorIconProvider
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppStringProvider
import cz.jvyh.o2.scratch.shared.common.ui.resources.CommonImageVectorIconProvider
import cz.jvyh.o2.scratch.shared.logging.ui.preview.composePreviewLoggingModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val composePreviewCommonModule = module {
    includes(
        composePreviewLoggingModule
    )

    single<AppDestinationToNavigateControllerImpl> { AppDestinationToNavigateControllerImpl() }
    single<AppDestinationToNavigateProvider> { get<AppDestinationToNavigateControllerImpl>() }
    single<AppDestinationToNavigateUpdater> { get<AppDestinationToNavigateControllerImpl>() }
    singleOf<AppStringProvider>(::ComposePreviewAppStringProvider)
    single<BusyIndicatorControllerImpl> { BusyIndicatorControllerImpl() }
    single<BusyIndicatorController> { get<BusyIndicatorControllerImpl>() }
    single<BusyIndicatorVisibilityProvider> { get<BusyIndicatorControllerImpl>() }
    single<BusyIndicatorInvalidator> { get<BusyIndicatorControllerImpl>() }
    single<CommonImageVectorIconProvider> { CommonImageVectorIconProvider() }
    singleOf<AppImageVectorIconProvider>(::ComposePreviewAppImageVectorIconProvider)
    singleOf<AppDrawableResourceIconProvider>(::ComposePreviewAppDrawableResourceIconProvider)
    single<DialogToShowControllerImpl> { DialogToShowControllerImpl() }
    single<DialogToShowProvider> { get<DialogToShowControllerImpl>() }
    single<DialogToShowUpdater> { get<DialogToShowControllerImpl>() }
    single<DispatcherProvider> { DefaultDispatcherProvider() }
}