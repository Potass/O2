package cz.jvyh.o2.scratch.common.platform.main

import cz.jvyh.o2.scratch.common.platform.shared.CodeFlowProvider
import cz.jvyh.o2.scratch.common.platform.shared.CodeValueProvider
import cz.jvyh.o2.scratch.common.platform.shared.IsActiveFlowProvider
import cz.jvyh.o2.scratch.common.platform.shared.IsActiveValueProvider

// TODO O2 - unit tests
internal class MainControllerImpl(
    private val isActiveFlowProvider: IsActiveFlowProvider,
    private val isActiveValueProvider: IsActiveValueProvider,
    private val codeFlowProvider: CodeFlowProvider,
    private val codeValueProvider: CodeValueProvider,
) : MainController,
    IsActiveFlowProvider by isActiveFlowProvider,
    IsActiveValueProvider by isActiveValueProvider,
    CodeFlowProvider by codeFlowProvider,
    CodeValueProvider by codeValueProvider