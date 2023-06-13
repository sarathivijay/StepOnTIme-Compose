package com.tws.stepontime.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tws.stepontime.R
import com.tws.stepontime.ui.commons.components.AppButton
import com.tws.stepontime.ui.theme.StepOnTimeComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onSignIn: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_login),
                contentDescription = "login screen background",
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splash),
                    contentDescription = "app logo"
                )
                Text(
                    text = stringResource(id = R.string.driver_app),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                )
                AppButton(text = R.string.signing_with_mobile) {
                    onSignIn.invoke()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLogin() {
    StepOnTimeComposeTheme() {
        LoginScreen {

        }
    }
}