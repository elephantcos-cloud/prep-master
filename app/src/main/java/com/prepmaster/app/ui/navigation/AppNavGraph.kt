package com.prepmaster.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.*
import com.prepmaster.app.data.repository.AppRepository
import com.prepmaster.app.ui.components.PrepBottomBar
import com.prepmaster.app.ui.screens.home.HomeScreen
import com.prepmaster.app.ui.screens.learn.LearnScreen
import com.prepmaster.app.ui.screens.learn.PrepDetailScreen
import com.prepmaster.app.ui.screens.practice.PracticeScreen
import com.prepmaster.app.ui.screens.quiz.QuizScreen
import com.prepmaster.app.ui.screens.flashcard.FlashCardScreen
import com.prepmaster.app.ui.screens.reference.ReferenceScreen
import com.prepmaster.app.ui.screens.stories.StoriesScreen
import com.prepmaster.app.ui.screens.profile.ProfileScreen

sealed class Screen(val route: String) {
    object Home      : Screen("home")
    object Learn     : Screen("learn")
    object Practice  : Screen("practice")
    object Quiz      : Screen("quiz")
    object FlashCard : Screen("flashcard")
    object Reference : Screen("reference")
    object Stories   : Screen("stories")
    object Profile   : Screen("profile")
    object PrepDetail: Screen("prep/{catId}") { fun go(catId: String) = "prep/$catId" }
}

private val bottomRoutes = setOf("home","learn","practice","quiz","flashcard","reference")

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val ctx  = LocalContext.current
    val repo = remember { AppRepository.get(ctx) }

    LaunchedEffect(Unit) { repo.ensureUser() }

    val back by navController.currentBackStackEntryAsState()
    val cur  = back?.destination?.route ?: "home"
    val showBottom = cur in bottomRoutes

    Scaffold(bottomBar = {
        if (showBottom) PrepBottomBar(cur) { route ->
            if (route == cur) return@PrepBottomBar
            navController.navigate(route) {
                popUpTo("home") { saveState = true; inclusive = route == "home" }
                launchSingleTop = true; restoreState = true
            }
        }
    }) { padding ->
        NavHost(navController, "home", Modifier.padding(padding)) {
            composable("home")      { HomeScreen(nav = navController) }
            composable("learn")     { LearnScreen(onCategory = { navController.navigate(Screen.PrepDetail.go(it)) }) }
            composable("practice")  { PracticeScreen() }
            composable("quiz")      { QuizScreen() }
            composable("flashcard") { FlashCardScreen() }
            composable("reference") { ReferenceScreen() }
            composable("stories")   { StoriesScreen() }
            composable("profile")   { ProfileScreen(onBack = { navController.popBackStack() }) }
            composable(Screen.PrepDetail.route,
                arguments = listOf(navArgument("catId") { type = NavType.StringType })) { b ->
                PrepDetailScreen(catId = b.arguments?.getString("catId") ?: "", onBack = { navController.popBackStack() })
            }
        }
    }
}
