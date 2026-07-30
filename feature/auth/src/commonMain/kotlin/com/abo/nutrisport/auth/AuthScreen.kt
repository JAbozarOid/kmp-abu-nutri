package com.abo.nutrisport.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abo.nutrisport.Alpha
import com.abo.nutrisport.BebasNeueFont
import com.abo.nutrisport.FontSize
import com.abo.nutrisport.Surface
import com.abo.nutrisport.SurfaceBrand
import com.abo.nutrisport.SurfaceError
import com.abo.nutrisport.TextPrimary
import com.abo.nutrisport.TextSecondary
import com.abo.nutrisport.TextWhite
import com.abo.nutrisport.auth.component.GoogleButton
import com.abo.nutrisport.auth.viewmodel.AuthViewModel
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@Composable
fun AuthScreen() {
    val viewModel = koinViewModel<AuthViewModel>()
    val messageBarState = rememberMessageBarState()
    var loadingState by remember { mutableStateOf(false) }

    Scaffold { paddingValues ->
        ContentWithMessageBar(
            contentBackgroundColor = Surface,
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
            messageBarState = messageBarState,
            errorMaxLines = 2,
            errorContainerColor = SurfaceError,
            errorContentColor = TextWhite,
            successContainerColor = SurfaceBrand,
            successContentColor = TextPrimary,


            ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "NUTRISPORT",
                        textAlign = TextAlign.Center,
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        color = TextSecondary
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth().alpha(Alpha.HALF),
                        text = "Sign In to Continue",
                        textAlign = TextAlign.Center,
                        fontSize = FontSize.EXTRA_REGULAR,
                        color = TextPrimary
                    )
                }
                GoogleButtonUiContainerFirebase(
                    linkAccount = false,
                    onResult = { result ->
                        result.onSuccess { user ->
                            loadingState = false
                            viewModel.createCustomer(
                                user = user,
                                onSuccess = {
                                    println("Authentication successful $user")
                                    messageBarState.addSuccess("Authentication successful")
                                },
                                onError = {message->
                                    println("Authentication failed $message")
                                    messageBarState.addError(message)
                                }
                            )
                        }.onFailure { error ->
                            if (error.message?.contains("A network error") == true) {
                                messageBarState.addError("Internet connection unavailable.")
                                println("Internet connection unavailable.")
                            } else if (error.message?.contains("Idtoken is null") == true) {
                                messageBarState.addError("Sign in cancel.")
                                println("ISign in cancel.")
                            } else {
                                messageBarState.addError(error.message ?: "unknown error.")
                                println("unknown error.")
                            }
                            loadingState = false
                        }
                    }
                ) {
                    GoogleButton(
                        loadingState = loadingState,
                        onCLick = {
                            loadingState = true
                            this@GoogleButtonUiContainerFirebase.onClick()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGoogleButton() {
    AuthScreen()
}