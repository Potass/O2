package cz.jvyh.o2.scratch.shared.common.domain

import cz.jvyh.o2.scratch.shared.common.ui.destination.AppDestination

sealed class AppDestinationToNavigate

data class TopLevelAppDestinationToNavigate(
    val destination: AppDestination<*>,
) : AppDestinationToNavigate()

data class InnerLevelAppDestinationToNavigate(
    val destination: AppDestination<*>,
) : AppDestinationToNavigate()

data class AutoLevelAppDestinationToNavigate(
    val topLevelDestination: AppDestination<*>,
    val innerLevelDestination: AppDestination<*>,
) : AppDestinationToNavigate()