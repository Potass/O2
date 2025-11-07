package cz.jvyh.o2.scratch.common.platform.main

import cz.jvyh.o2.scratch.common.platform.shared.CodeFlowProvider
import cz.jvyh.o2.scratch.common.platform.shared.CodeValueProvider
import cz.jvyh.o2.scratch.common.platform.shared.IsActiveFlowProvider
import cz.jvyh.o2.scratch.common.platform.shared.IsActiveValueProvider

internal interface MainController : IsActiveFlowProvider, IsActiveValueProvider, CodeFlowProvider, CodeValueProvider