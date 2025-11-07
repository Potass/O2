package cz.jvyh.o2.scratch.common.ui.activation

import androidx.lifecycle.ViewModel
import cz.jvyh.o2.scratch.common.platform.activation.ActivationController

internal class ActivationViewModel(
    private val controller: ActivationController,
) : ViewModel() {
    fun onActivateClicked() = controller.activate()
}