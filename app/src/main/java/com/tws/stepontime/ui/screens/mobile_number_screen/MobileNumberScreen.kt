package com.tws.stepontime.ui.screens.mobile_number_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tws.stepontime.R
import com.tws.stepontime.domain.events.MobileNumberEvents
import com.tws.stepontime.domain.usecase.MobileNumberValidationUseCase
import com.tws.stepontime.domain.viewmodel.MobileNumberViewModel
import com.tws.stepontime.ui.commons.components.AppButton
import com.tws.stepontime.ui.commons.components.MyAppBar
import com.tws.stepontime.ui.theme.StepOnTimeComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileNumberScreen(
    modifier: Modifier,
    viewModel: MobileNumberViewModel = viewModel(
        factory = viewModelFactory(
            MobileNumberValidationUseCase()
        )
    ),
    onBackPress: (() -> Unit),
    onNextClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MyAppBar { onBackPress.invoke() }
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
//                .padding(horizontal = 10)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_login),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .wrapContentSize()
                    .padding(horizontal = 20.dp)

            ) {
                Text(
                    modifier = modifier.padding(horizontal = 25.dp),
                    text = stringResource(R.string.enter_registered_mobile_number),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                )
                ShowOutlinedTextField(viewModel, modifier)
                Text(
                    text = stringResource(R.string.mobile_number_screen_info),
                    style = MaterialTheme.typography.displayMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    ),
                    modifier = modifier.wrapContentWidth()
                )
                Spacer(modifier = modifier.weight(1f))
                AppButton(text = R.string.next) {
                    viewModel
                    onNextClick.invoke()
                }
                Spacer(modifier = modifier.height(20.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowOutlinedTextField(
    viewModel: MobileNumberViewModel,
    modifier: Modifier,
) {


    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    /*
        val keyboard = LocalSoftwareKeyboardController.current

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
            delay(100)
            keyboard?.show()
        }
    */

    OutlinedTextField(
        value = uiState.text,
        onValueChange = {
            viewModel.onMobileScreenEvents(MobileNumberEvents.onNewText(it))
        },
        textStyle = MaterialTheme.typography.displayMedium.copy(
            color = MaterialTheme.colorScheme.onPrimary
        ),
        label = {
            Text(
                text = stringResource(id = R.string.mobile_number),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                )
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.enter_your_number),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                ),
                modifier = modifier.focusRequester(focusRequester = focusRequester)
            )
        },
        leadingIcon = { Text(text = "+91") },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            //close keyboard
            focusManager.clearFocus()
        }),
        singleLine = true,
        maxLines = 1,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewOutlinedTextField() {
    StepOnTimeComposeTheme {
//        ShowOutlinedTextField(MobileNumberViewModel(), Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMobileNumberScreen() {
    StepOnTimeComposeTheme {
        MobileNumberScreen(
            modifier = Modifier,
            onBackPress = { },
            onNextClick = { }
        )
    }
}