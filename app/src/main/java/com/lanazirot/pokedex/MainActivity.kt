package com.lanazirot.pokedex

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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

            val routesWithoutNavBarBottom = listOf (
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
                                content = {  AppNavGraph(globalProvider = gp) }
                            )
                        }
                    }
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            val decorView: View = window.getDecorView()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        }
    }
}

