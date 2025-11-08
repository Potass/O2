@file:OptIn(ExperimentalMaterial3Api::class)

package cz.jvyh.o2.scratch.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.ui.activation.ActivationDestination
import cz.jvyh.o2.scratch.common.ui.activation.ActivationScreen
import cz.jvyh.o2.scratch.common.ui.main.MainDestination
import cz.jvyh.o2.scratch.common.ui.main.MainScreen
import cz.jvyh.o2.scratch.common.ui.scratch.ScratchDestination
import cz.jvyh.o2.scratch.common.ui.scratch.ScratchScreen
import cz.jvyh.o2.scratch.common.ui.theming.PlatformAppTheme
import cz.jvyh.o2.scratch.shared.common.domain.CommonImageVectorIconKey
import cz.jvyh.o2.scratch.shared.common.domain.StringKey
import cz.jvyh.o2.scratch.shared.common.ui.composables.collectAsStateWithLifecycleMultiplatform
import cz.jvyh.o2.scratch.shared.common.ui.destination.AppDestination
import cz.jvyh.o2.scratch.shared.common.ui.resources.iconKeyResource
import cz.jvyh.o2.scratch.shared.common.ui.resources.stringKeyResource
import cz.jvyh.o2.scratch.shared.common.ui.theming.ContrastLevel

@Composable
actual fun AppContent(
    viewModel: AppContentViewModel,
    logger: Logger,
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycleMultiplatform()

    PlatformAppTheme(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = true,
        contrastLevel = ContrastLevel.High
    ) {
        Surface {
            val navController = rememberNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentDestination = Destinations.all.find { currentBackStack?.destination?.route == it.route } ?: Destinations.default

            ProcessingOverlay(isProcessing = state.isBusyIndicatorVisible) {
                AppDialog(
                    id = state.dialogToShowId,
                    onDismiss = viewModel::invalidateDialogToShowId,
                    isProcessing = state.isBusyIndicatorVisible
                )

                Scaffold(
                    topBar = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            TopAppBar(
                                title = { Text(text = stringKeyResource(currentDestination.title)) },
                                navigationIcon = {
                                    NavigationIcon(
                                        navController = navController,
                                        currentDestination = currentDestination
                                    )
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Destinations.default.route
                        ) {
                            composable(route = MainDestination.route) {
                                MainScreen(
                                    onScratchClicked = { navController.navigate(route = ScratchDestination.route) },
                                    onActivateClicked = { navController.navigate(route = ActivationDestination.route) }
                                )
                            }

                            composable(route = ScratchDestination.route) {
                                ScratchScreen()
                            }

                            composable(route = ActivationDestination.route) {
                                ActivationScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NavigationIcon(
    navController: NavHostController,
    currentDestination: AppDestination<out StringKey>,
) {
    if (navController.previousBackStackEntry != null && currentDestination !in Destinations.topLevel) {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = iconKeyResource(CommonImageVectorIconKey.AutoMirroredFilledArrowBackImageVectorIcon),
                contentDescription = null
            )
        }
    }
}

private object Destinations {
    val default = MainDestination
    val all: List<AppDestination<*>> = listOf(
        MainDestination,
        ScratchDestination,
        ActivationDestination
    )
    val topLevel: List<AppDestination<*>> = listOf(
        MainDestination
    )
}