package com.tws.stepontime.ui.commons.components

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tws.stepontime.R
import com.tws.stepontime.ui.theme.StepOnTimeComposeTheme

@Composable
fun AppButton(
    @StringRes text: Int,
    onClick: () -> Unit,
) {
    Button(
        shape = RoundedCornerShape(10.dp),
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary,
            )
        )
    }
}

@Preview
@Composable
fun PreviewAppButton() {
    StepOnTimeComposeTheme {
        AppButton(text = R.string.signing_with_mobile) {

        }
    }
}