package cz.jvyh.o2.scratch.common.platform

import cz.jvyh.o2.scratch.shared.common.platform.AppDestinationToNavigateProvider
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorVisibilityProvider
import cz.jvyh.o2.scratch.shared.common.platform.DialogToShowProvider

interface AppContentController : BusyIndicatorVisibilityProvider, DialogToShowProvider, AppDestinationToNavigateProvider