package cz.jvyh.o2.scratch.shared.common.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import cz.jvyh.o2.scratch.shared.common.domain.CommonStringKey
import cz.jvyh.o2.scratch.shared.common.domain.IconKey
import cz.jvyh.o2.scratch.shared.common.domain.StringKey
import cz.jvyh.o2.scratch.shared.common.ui.resources.iconKeyResource
import cz.jvyh.o2.scratch.shared.common.ui.resources.stringKeyResource

@Composable
fun AppAlertDialog(
    icon: IconKey? = null,
    iconContentDescription: StringKey? = null,
    titleText: StringKey? = null,
    bodyText: StringKey? = null,
    confirmButtonText: StringKey = CommonStringKey.CommonLabelOk,
    confirmButtonEnabled: Boolean = true,
    onConfirm: () -> Unit = {},
    dismissButtonText: StringKey? = null,
    onDismiss: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = icon?.let {
            {
                Icon(
                    imageVector = iconKeyResource(it),
                    contentDescription = iconContentDescription?.let { d -> stringKeyResource(d) }
                )
            }
        },
        title = titleText?.let { { Text(text = stringKeyResource(it)) } },
        text = bodyText?.let { { Text(text = stringKeyResource(it)) } },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                enabled = confirmButtonEnabled
            ) {
                Text(text = stringKeyResource(confirmButtonText))
            }
        },
        dismissButton = dismissButtonText?.let { { TextButton(onClick = onDismiss) { Text(text = stringKeyResource(it)) } } }
    )
}