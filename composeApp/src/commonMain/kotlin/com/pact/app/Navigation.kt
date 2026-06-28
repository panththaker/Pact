package com.pact.app

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pact.app.auth.presentation.AuthViewModel
import com.pact.app.auth.presentation.LoginScreenRoot
import com.pact.app.auth.presentation.OpeningScreen
import com.pact.app.auth.presentation.SignUpScreenRoot
import com.pact.app.calendar.presentation.CalendarScreenRoot
import com.pact.app.calendar.presentation.CalendarViewModel
import com.pact.app.calendar.presentation.event.EventFormScreenRoot
import com.pact.app.calendar.presentation.event.EventViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable object Opening
@Serializable object SignUp

@Serializable object Login{}
@Serializable object Calendar
@Serializable object Todo
@Serializable object Chat
@Serializable object Profile

@Serializable data class EventForm(val eventId: String? = null)


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Opening,
        enterTransition = { fadeIn(animationSpec = tween(150)) },
        exitTransition = { fadeOut(animationSpec = tween(150)) },
        popEnterTransition = { fadeIn(animationSpec = tween(150)) },
        popExitTransition = { fadeOut(animationSpec = tween(150)) }

    ){
        composable<Opening>{
            OpeningScreen(
                onGetStarted = {navController.navigate(SignUp)},
                onLoginClick = {navController.navigate(Login)}

            )
        }
        composable<SignUp> {
            val viewModel = koinViewModel<AuthViewModel>()
            SignUpScreenRoot(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onLoginClick = {navController.navigate(Login)},
                navToCalendar = {navController.navigate(Calendar)}

            )
        }
        composable<Login> {
            val viewModel = koinViewModel<AuthViewModel>()
            LoginScreenRoot(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onSignupClick = {navController.navigate(SignUp)},
                navToCalendar = {navController.navigate(Calendar)}
            )
        }

        composable<Calendar> {
            val viewModel = koinViewModel<CalendarViewModel>()
            CalendarScreenRoot(
                viewModel = viewModel,
                onNavigateToTodo = { navController.navigate(Todo) },
                onNavigateToChat = { navController.navigate(Chat) },
                onNavigateToProfile = { navController.navigate(Profile) },
                onNavigateToCreateEvent = { navController.navigate(EventForm(eventId = null)) }
            )
        }
        composable<EventForm> { backStackEntry ->
            val args = backStackEntry.toRoute<EventForm>()   // ← getting data IN
            val viewModel = koinViewModel<EventViewModel>()
            EventFormScreenRoot(
                viewModel = viewModel,
                eventId = args.eventId,                       // ← passed in
                onBack = { navController.popBackStack() }      // ← getting OUT
            )
        }

        composable<Todo> { }
        composable<Chat> { }
        composable<Profile> { }

    }
}


// TODO: Make the navbar on each individual screen through individual scaffolds and make the bottom bar a component on a core file or smth along those lines
// TODO: Make a new composables for each page
//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    var selectedTab by remember { mutableStateOf(NavTab.CALENDAR) }
//
//    Scaffold(
//        bottomBar = {
//            PactNavBar(
//                selectedTab = selectedTab,
//                onTabSelected = { tab ->
//                    selectedTab = tab
//                    when (tab) {
//                        NavTab.CALENDAR -> navController.navigate(Calendar)
//                        NavTab.TODO -> navController.navigate(Todo)
//                        NavTab.CHAT -> navController.navigate(Chat)
//                        NavTab.PROFILE -> navController.navigate(Profile)
//                    }
//                }
//            )
//        },
//        contentWindowInsets = WindowInsets.safeDrawing
//
//    ) { paddingValues ->
//        NavHost(
//            navController = navController,
//            startDestination = Calendar,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(bottom = paddingValues.calculateBottomPadding())
//        ) {
//            composable<Calendar> {
//                val viewModel = koinViewModel<CalendarViewModel>()
//                CalendarScreenRoot(viewModel)
//            }
//            composable<Todo> { /* TodoScreen() */ }
//            composable<Chat> { /* ChatScreen() */ }
//            composable<Profile> { /* ProfileScreen() */ }
//        }
//    }
//}