package com.tws.stepontime.ui.commons.nav_graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tws.stepontime.ui.screens.login.LoginScreen
import com.tws.stepontime.ui.screens.mobile_number_screen.MobileNumberScreen
import com.tws.stepontime.ui.screens.otp.OtpScreen

fun NavGraphBuilder.authGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        startDestination = "login_screen",
        route = "Auth_Graph"
    ) {
        composable(route = "login_screen") {
            LoginScreen(modifier = modifier) {
                navController.navigate(route = "mobile_number_screen")
            }
        }

        composable(route = "mobile_number_screen") {
            MobileNumberScreen(
                modifier = modifier,
                onBackPress = {
                    navController.navigateUp()
                },
                onNextClick = {
                    navController.navigate(route = "otp_screen")
                }
            )
        }

        composable(route = "otp_screen") {
            OtpScreen(modifier = modifier) {
                navController.navigateUp()
            }
        }
    }
}
