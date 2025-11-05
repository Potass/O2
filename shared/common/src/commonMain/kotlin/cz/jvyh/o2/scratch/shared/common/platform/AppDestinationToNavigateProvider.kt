package cz.jvyh.o2.scratch.shared.common.platform

import cz.jvyh.o2.scratch.shared.common.domain.AppDestinationToNavigate
import kotlinx.coroutines.flow.Flow

interface AppDestinationToNavigateProvider {
    val destinationToNavigateFlow: Flow<AppDestinationToNavigate>
}