package cz.jvyh.o2.scratch.common.di

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
import cz.jvyh.o2.scratch.common.ui.resources.AppDrawableResourceIconProviderImpl
import cz.jvyh.o2.scratch.common.ui.resources.AppImageVectorIconProviderImpl
import cz.jvyh.o2.scratch.common.ui.resources.AppStringProviderImpl
import cz.jvyh.o2.scratch.common.ui.resources.CommonDrawableResourceIconProviderImpl
import cz.jvyh.o2.scratch.common.ui.scratch.ScratchViewModel
import cz.jvyh.o2.scratch.shared.common.di.commonModule
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppDrawableResourceIconProvider
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppImageVectorIconProvider
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppStringProvider
import cz.jvyh.o2.scratch.shared.common.ui.resources.CommonDrawableResourceIconProvider
import cz.jvyh.o2.scratch.shared.logging.infrastructure.withClassNameTag
import cz.jvyh.o2.scratch.shared.networking.di.networkingModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {
    includes(
        commonModule,
        networkingModule,
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
    single<AppStringProvider> { AppStringProviderImpl(get<Logger>().withClassNameTag<AppStringProviderImpl>(), get()) }
    single<AppImageVectorIconProvider> { AppImageVectorIconProviderImpl(get()) }
    single<CommonDrawableResourceIconProvider> { CommonDrawableResourceIconProviderImpl() }
    single<AppDrawableResourceIconProvider> { AppDrawableResourceIconProviderImpl(get()) }
}

@Suppress("KotlinNoActualForExpect")
expect val appPlatformModule: Module