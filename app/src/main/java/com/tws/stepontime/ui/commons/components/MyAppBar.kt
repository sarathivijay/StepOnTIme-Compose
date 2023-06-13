package com.tws.stepontime.ui.commons.components

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tws.stepontime.R
import com.tws.stepontime.ui.theme.StepOnTimeComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    @StringRes title: Int? = null,
    onBackPress: () -> Unit,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            AppBackButton {
                onBackPress.invoke()
            }
        },
        title = {
            Text(
                text = if (title == null) "" else stringResource(id = title),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                ),
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Preview
@Composable
fun PreviewMyAppBar() {
    StepOnTimeComposeTheme {
        MyAppBar(R.string.otp) { }
    }
}