package com.lanazirot.pokedex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.lanazirot.pokedex.ui.navigation.AppNavGraph
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.providers.AppProvider
import com.lanazirot.pokedex.ui.providers.GlobalProvider
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider
import com.lanazirot.pokedex.ui.providers.UserProvider
import com.lanazirot.pokedex.ui.screens.bottomappbar.BottomNavBar
import com.lanazirot.pokedex.ui.screens.bottomappbar.NavigationCenterButton
import com.lanazirot.pokedex.ui.screens.user.UserViewModel
import com.lanazirot.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val bottomBarVisibility = remember { mutableStateOf(true) }
            val navController = rememberNavController()

            val routesWithoutNavBarBottom = listOf(
                AppRoutes.Login.Login,
                AppRoutes.Play.Game
            )

            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomBarVisibility.value = destination.route !in routesWithoutNavBarBottom
            }

            val gp = AppProvider(navigation = navController)
            val gup = UserProvider(currentUser = userViewModel)

            PokedexTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    CompositionLocalProvider(
                        GlobalUserProvider provides gup
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            Scaffold(
                                bottomBar = { if (bottomBarVisibility.value) BottomNavBar() },
                                floatingActionButtonPosition = FabPosition.Center,
                                isFloatingActionButtonDocked = true,
                                floatingActionButton = { if (bottomBarVisibility.value) NavigationCenterButton() },
                                content = {
                                    Column(
                                        modifier = Modifier.padding(bottom = if (bottomBarVisibility.value) 65.dp else 0.dp).fillMaxSize()
                                    ) {
                                        AppNavGraph(globalProvider = gp)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        hideSystemUI()
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}

