package cz.jvyh.o2.scratch.shared.common.platform

import cz.jvyh.o2.scratch.shared.common.domain.AppDestinationToNavigate

interface AppDestinationToNavigateUpdater {
    fun updateDestinationToNavigate(destinationToNavigate: AppDestinationToNavigate)
}