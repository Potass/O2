package cz.jvyh.o2.scratch.common.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.jvyh.o2.scratch.shared.common.domain.CommonStringKey
import cz.jvyh.o2.scratch.shared.common.ui.constants.SpacingDimens
import cz.jvyh.o2.scratch.shared.common.ui.resources.stringKeyResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun MainScreen(
    onScratchClicked: () -> Unit,
    onActivateClicked: () -> Unit,
    viewModel: MainViewModel = koinViewModel(),
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(SpacingDimens.Default)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(SpacingDimens.DefaultS)
        ) {
            Card(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Inactive",
                    modifier = Modifier.padding(SpacingDimens.DefaultS)
                )
            }
            Button(
                onClick = onScratchClicked,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = stringKeyResource(CommonStringKey.CommonLabelScratch))
            }
            Button(
                onClick = onActivateClicked,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = stringKeyResource(CommonStringKey.CommonLabelActivate))
            }
        }
    }
}