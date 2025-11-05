package cz.jvyh.o2.scratch.shared.common.platform

import cz.jvyh.o2.scratch.shared.common.domain.AppDestinationToNavigate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class AppDestinationToNavigateControllerImpl : AppDestinationToNavigateProvider, AppDestinationToNavigateUpdater {
    private val _destinationToNavigateFlow = MutableSharedFlow<AppDestinationToNavigate>(extraBufferCapacity = 1)
    override val destinationToNavigateFlow: Flow<AppDestinationToNavigate> = _destinationToNavigateFlow

    override fun updateDestinationToNavigate(destinationToNavigate: AppDestinationToNavigate) {
        _destinationToNavigateFlow.tryEmit(destinationToNavigate)
    }
}