package cz.jvyh.o2.scratch.common.ui.preview

import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.platform.AppContentController
import cz.jvyh.o2.scratch.common.platform.AppContentControllerImpl
import cz.jvyh.o2.scratch.common.ui.AppContentViewModel
import cz.jvyh.o2.scratch.common.ui.activation.ActivationViewModel
import cz.jvyh.o2.scratch.common.ui.main.MainViewModel
import cz.jvyh.o2.scratch.common.ui.resources.CommonDrawableResourceIconProviderImpl
import cz.jvyh.o2.scratch.common.ui.scratch.ScratchViewModel
import cz.jvyh.o2.scratch.shared.common.ui.preview.composePreviewCommonModule
import cz.jvyh.o2.scratch.shared.common.ui.resources.CommonDrawableResourceIconProvider
import cz.jvyh.o2.scratch.shared.logging.infrastructure.withClassNameTag
import cz.jvyh.o2.scratch.shared.networking.ui.preview.composePreviewNetworkingModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val composePreviewAppModule = module {
    includes(
        composePreviewCommonModule,
        composePreviewNetworkingModule
    )

    // App content
    singleOf(::AppContentControllerImpl) { bind<AppContentController>() }
    viewModel { AppContentViewModel(get<Logger>().withClassNameTag<AppContentViewModel>(), get()) }
    // Main screen
    viewModel { MainViewModel(get<Logger>().withClassNameTag<MainViewModel>()) }
    // Scratch screen
    viewModel { ScratchViewModel(get<Logger>().withClassNameTag<ScratchViewModel>()) }
    // Activation screen
    viewModel { ActivationViewModel(get<Logger>().withClassNameTag<ActivationViewModel>()) }
    // Resources
    single<CommonDrawableResourceIconProvider> { CommonDrawableResourceIconProviderImpl() }
}