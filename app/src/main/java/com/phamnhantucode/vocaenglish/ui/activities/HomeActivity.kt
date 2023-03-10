package com.phamnhantucode.vocaenglish.ui.activities

import com.phamnhantucode.vocaenglish.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.phamnhantucode.vocaenglish.ui.navigations.HomeActivityGraph
import com.phamnhantucode.vocaenglish.ui.navigations.Screens
import com.phamnhantucode.vocaenglish.ui.theme.VOCAEnglishTheme
import com.phamnhantucode.vocaenglish.utils.BottomBarItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VOCAEnglishTheme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Mainss() {
    VOCAEnglishTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    var navController = rememberNavController()
    var scaffoldState = rememberScaffoldState()
    var scope = rememberCoroutineScope()

    val navigation_items = listOf<BottomBarItem>(
        BottomBarItem(Screens.HomeScreen.route, R.drawable.ic_baseline_home_24, "Home"),
        BottomBarItem(Screens.StudyScreen.route, R.drawable.ic_baseline_home_24, "Study"),
        BottomBarItem(Screens.WorldScreen.route, R.drawable.ic_baseline_home_24, "World"),
        BottomBarItem(Screens.AccountScreen.route, R.drawable.ic_baseline_home_24, "Account")
    )
    val bottomBarState = rememberSaveable {
        mutableStateOf(true)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(key1 = navBackStackEntry) {
        when (navBackStackEntry?.destination?.route) {
            Screens.DictionaryScreen.route -> {
                bottomBarState.value = false
            }
            else -> {
                bottomBarState.value = true
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(
                navController = navController,
                menu_items = navigation_items,
                bottomBarState = bottomBarState
            )
        },
        floatingActionButton = {
            FindActionButton(navController, scaffoldState, scope, bottomBarState = bottomBarState)
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,

        ) {
        HomeActivityGraph(navController = navController)
    }
}

@Composable
fun CurrentRoute(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}

@Composable
fun BottomNavigation(
    navController: NavHostController,
    menu_items: List<BottomBarItem>,
    bottomBarState: MutableState<Boolean>
) {
    AnimatedVisibility(visible = bottomBarState.value,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        BottomAppBar(
            cutoutShape = MaterialTheme.shapes.small.copy(
                CornerSize(50)
            )
        ) {
            BottomNavigation(
            ) {
                val currentRoute = CurrentRoute(navController = navController)
                for (i in 0 until menu_items.size / 2) {
                    BottomNavigationItem(
                        selected = currentRoute == menu_items[i].route, onClick = {
                            navController.navigate(menu_items[i].route)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = menu_items[i].iconId),
                                contentDescription = menu_items[i].title
                            )
                        },
                        label = {
                            Text(
                                text = menu_items[i].title,
                                style = MaterialTheme.typography.body2,
                                fontSize = 12.sp
                            )
                        },
                        alwaysShowLabel = false
                    )
                }
                Spacer(modifier = Modifier.width(70.dp))
                for (i in menu_items.size / 2 until menu_items.size) {
                    BottomNavigationItem(
                        selected = currentRoute == menu_items[i].route, onClick = {
                            navController.navigate(menu_items[i].route)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = menu_items[i].iconId),
                                contentDescription = menu_items[i].title
                            )
                        },
                        label = {
                            Text(
                                text = menu_items[i].title,
                                style = MaterialTheme.typography.body2,
                                fontSize = 12.sp
                            )
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    }
}

@Composable
fun FindActionButton(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    bottomBarState: MutableState<Boolean>
) {
    AnimatedVisibility(
        visible = bottomBarState.value, enter = fadeIn(
            animationSpec = tween(500)
        ),
        exit = fadeOut(tween(500))
    ) {
        FloatingActionButton(onClick = {
            navController.navigate(Screens.DictionaryScreen.route)
//        scope.launch {
//            scaffoldState.snackbarHostState.showSnackbar(
//                "asdasdsadasdasd",
//                "ok",
//                SnackbarDuration.Indefinite,
//
//                )
//        }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    VOCAEnglishTheme {
//        Greeting("Android")
//    }
//}