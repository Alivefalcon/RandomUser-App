package tees.ac.uk.w9637327.randomuserapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import tees.ac.uk.w9637327.randomuserapp.common.AuthenticationErrorDialog
import tees.ac.uk.w9637327.randomuserapp.common.AuthenticationForm
import tees.ac.uk.w9637327.randomuserapp.state.authenticationstate.AuthenticationEvent
import tees.ac.uk.w9637327.randomuserapp.state.authenticationstate.AuthenticationState


@Composable
fun Authenticationscreen(
    modifier: Modifier = Modifier,
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (authenticationState.isLoading) {
            CircularProgressIndicator()
        } else {
            AuthenticationForm(
                authenticationMode = authenticationState.authenticationMode,
                email = authenticationState.email,
                password = authenticationState.password,
                onEmailChanged = {
                    handleEvent(AuthenticationEvent.EmailChanged(it))
                },
                onPasswordChanged = {
                    handleEvent(
                        AuthenticationEvent.PasswordChanged(
                            it
                        )
                    )
                },
                onToggleAthMode = { handleEvent(AuthenticationEvent.ToggleAuthenticationMode) },
                passwordSatisfiedRequirement = authenticationState.passwordRequirements,
                onAuthenticate = { handleEvent(AuthenticationEvent.Authenticate) },
                enableAuthentication = authenticationState.isFormValid()
            )
            authenticationState.error?.let {
                AuthenticationErrorDialog(error = it) {
                    handleEvent(AuthenticationEvent.ErrorDismissed)
                }
            }
        }
    }
}