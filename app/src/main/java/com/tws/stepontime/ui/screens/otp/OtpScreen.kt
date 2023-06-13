package com.tws.stepontime.ui.screens.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tws.stepontime.R
import com.tws.stepontime.domain.events.OtpEvents
import com.tws.stepontime.domain.viewmodel.MobileNumberViewModel
import com.tws.stepontime.ui.commons.components.AppButton
import com.tws.stepontime.ui.commons.components.MyAppBar
import com.tws.stepontime.ui.screens.otp.component.OtpTextField
import com.tws.stepontime.ui.theme.StepOnTimeComposeTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(
    modifier: Modifier,
    viewModel: MobileNumberViewModel = viewModel(),
    onBackPress: () -> Unit,
) {

    val uiState by viewModel.otpState.collectAsState()
    var isTimerRunning by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(isTimerRunning) {
        while (isTimerRunning) {
            if (uiState.timerSeconds == 1) {
                isTimerRunning = false
            } else {
                delay(1000L)
                viewModel.onOtpEvent(OtpEvents.onTimerChange(uiState.timerSeconds.minus(1)))
            }
        }
    }

    StepOnTimeComposeTheme {
        Scaffold(
            topBar = {
                MyAppBar(R.string.otp) {
                    onBackPress()
                }
            }
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splash),
                    contentDescription = null
                )
                Text(
                    text = "Verification Code",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = 0.8f
                        )
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "We have sent the verification to \nYour Mobile Number",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = 0.8f
                        )
                    ),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        )
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "+91 8667858046")
                    IconButton(onClick = {
                        onBackPress.invoke()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                OtpTextField(
                    otpText = uiState.value,
                    onOtpTextChange = { value, isLastField ->
                        viewModel.onOtpEvent(OtpEvents.onNewValueChanged(value))
                        if (isLastField) {
                            viewModel.validateOtp(value)
                        }
                    },
                    modifier = modifier
                )
                TimerComposable(isTimerRunning, viewModel) {
                    isTimerRunning = true
                }
                AppButton(text = R.string.submit) {

                }
            }
        }
    }


}

@Composable
fun TimerComposable(
    isTimerRunning: Boolean = true,
    viewModel: MobileNumberViewModel,
    onResendClick: () -> Unit,
) {
    val otpState by viewModel.otpState.collectAsState()
    if (!isTimerRunning) {
        Text(
            text = "Resend",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clickable {
                    viewModel.onOtpEvent(OtpEvents.onTimerChange(10))
                    onResendClick.invoke()
                },
            textAlign = TextAlign.End
        )
    } else {
        Text(text = "Resend OTP in ${otpState.timerSeconds} seconds")
    }
}


@Preview
@Composable
fun PreviewOtpScreen() {
    StepOnTimeComposeTheme {
        OtpScreen(modifier = Modifier) {

        }
    }
}

