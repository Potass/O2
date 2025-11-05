package cz.jvyh.o2.scratch.shared.common.ui.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.jvyh.o2.scratch.shared.common.domain.CommonImageVectorIconKey
import cz.jvyh.o2.scratch.shared.common.domain.CommonStringKey
import cz.jvyh.o2.scratch.shared.common.domain.IconKey
import cz.jvyh.o2.scratch.shared.common.domain.StringKey
import cz.jvyh.o2.scratch.shared.common.ui.composables.AppAlertDialog

@Preview
@Composable
fun MinimumAppAlertDialogPreview(
    confirmButtonText: StringKey = CommonStringKey.CommonLabelOk,
    onConfirm: () -> Unit = {},
) {
    Surface {
        KoinAwarePreview(composePreviewCommonModule) {
            AppAlertDialog(
                confirmButtonText = confirmButtonText,
                onConfirm = onConfirm
            )
        }
    }
}

@Preview
@Composable
fun BodyOnlyAppAlertDialogPreview(
    bodyText: StringKey = CommonStringKey.CommonLabelOk,
    confirmButtonText: StringKey = CommonStringKey.CommonLabelOk,
    onConfirm: () -> Unit = {},
) {
    Surface {
        KoinAwarePreview(composePreviewCommonModule) {
            AppAlertDialog(
                bodyText = bodyText,
                confirmButtonText = confirmButtonText,
                onConfirm = onConfirm
            )
        }
    }
}

@Preview
@Composable
fun TitleOnlyAppAlertDialogPreview(
    titleText: StringKey = CommonStringKey.CommonLabelOk,
    confirmButtonText: StringKey = CommonStringKey.CommonLabelOk,
    onConfirm: () -> Unit = {},
) {
    Surface {
        KoinAwarePreview(composePreviewCommonModule) {
            AppAlertDialog(
                titleText = titleText,
                confirmButtonText = confirmButtonText,
                onConfirm = onConfirm
            )
        }
    }
}

@Preview
@Composable
fun IconOnlyAppAlertDialogPreview(
    icon: IconKey = CommonImageVectorIconKey.AutoMirroredFilledArrowBackImageVectorIcon,
    confirmButtonText: StringKey = CommonStringKey.CommonLabelOk,
    onConfirm: () -> Unit = {},
) {
    Surface {
        KoinAwarePreview(composePreviewCommonModule) {
            AppAlertDialog(
                icon = icon,
                confirmButtonText = confirmButtonText,
                onConfirm = onConfirm
            )
        }
    }
}

@Preview
@Composable
fun TitleAndBodyOnlyAppAlertDialogPreview(
    titleText: StringKey = CommonStringKey.CommonLabelOk,
    bodyText: StringKey = CommonStringKey.CommonLabelOk,
    confirmButtonText: StringKey = CommonStringKey.CommonLabelOk,
    onConfirm: () -> Unit = {},
) {
    Surface {
        KoinAwarePreview(composePreviewCommonModule) {
            AppAlertDialog(
                titleText = titleText,
                bodyText = bodyText,
                confirmButtonText = confirmButtonText,
                onConfirm = onConfirm
            )
        }
    }
}

@Preview
@Composable
fun FullAppAlertDialogPreview(
    icon: IconKey = CommonImageVectorIconKey.AutoMirroredFilledArrowBackImageVectorIcon,
    titleText: StringKey = CommonStringKey.CommonLabelOk,
    bodyText: StringKey = CommonStringKey.CommonLabelOk,
    confirmButtonText: StringKey = CommonStringKey.CommonLabelOk,
    onConfirm: () -> Unit = {},
    dismissButtonText: StringKey = CommonStringKey.CommonLabelOk,
) {
    Surface {
        KoinAwarePreview(composePreviewCommonModule) {
            AppAlertDialog(
                icon = icon,
                titleText = titleText,
                bodyText = bodyText,
                confirmButtonText = confirmButtonText,
                onConfirm = onConfirm,
                dismissButtonText = dismissButtonText
            )
        }
    }
}