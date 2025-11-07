package cz.jvyh.o2.scratch.common.ui.preview

import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.dataaccess.activation.ActivationProcessorImpl
import cz.jvyh.o2.scratch.common.platform.AppContentController
import cz.jvyh.o2.scratch.common.platform.AppContentControllerImpl
import cz.jvyh.o2.scratch.common.platform.activation.ActivationController
import cz.jvyh.o2.scratch.common.platform.activation.ActivationControllerImpl
import cz.jvyh.o2.scratch.common.platform.activation.ActivationProcessor
import cz.jvyh.o2.scratch.common.platform.main.MainController
import cz.jvyh.o2.scratch.common.platform.main.MainControllerImpl
import cz.jvyh.o2.scratch.common.platform.scratch.ScratchController
import cz.jvyh.o2.scratch.common.platform.scratch.ScratchControllerImpl
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
import org.koin.core.module.dsl.factoryOf
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
    // Main
    viewModel { MainViewModel(get<Logger>().withClassNameTag<MainViewModel>(), get()) }
    singleOf(::MainControllerImpl) { bind<MainController>() }
    // Scratch
    viewModel { ScratchViewModel(get<Logger>().withClassNameTag<ScratchViewModel>(), get()) }
    singleOf(::ScratchControllerImpl) { bind<ScratchController>() }
    // Activation
    viewModel { ActivationViewModel(get<Logger>().withClassNameTag<ActivationViewModel>(), get()) }
    singleOf(::ActivationControllerImpl) { bind<ActivationController>() }
    factoryOf(::ActivationProcessorImpl) { bind<ActivationProcessor>() }
    // Resources
    single<CommonDrawableResourceIconProvider> { CommonDrawableResourceIconProviderImpl() }
}