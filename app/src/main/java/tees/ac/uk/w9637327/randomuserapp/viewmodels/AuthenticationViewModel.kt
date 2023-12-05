package tees.ac.uk.w9637327.randomuserapp.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tees.ac.uk.w9637327.randomuserapp.enums.AuthenticationMode
import tees.ac.uk.w9637327.randomuserapp.enums.PasswordRequirement
import tees.ac.uk.w9637327.randomuserapp.firebase.CreateUserInFirebase
import tees.ac.uk.w9637327.randomuserapp.firebase.LoginFirebase
import tees.ac.uk.w9637327.randomuserapp.firebase.RandomUserFirebase
import tees.ac.uk.w9637327.randomuserapp.state.authenticationstate.AuthenticationEvent
import tees.ac.uk.w9637327.randomuserapp.state.authenticationstate.AuthenticationState

class AuthenticationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AuthenticationState())
    val uiState = _uiState.asStateFlow()


    private fun toggleAuthenticationMode() {
        val authenticationMode = uiState.value.authenticationMode

        val newAuthenticationMode = if (authenticationMode == AuthenticationMode.SIGN_IN) {
            AuthenticationMode.SIGN_UP
        } else AuthenticationMode.SIGN_IN

        _uiState.update {
            it.copy(
                authenticationMode = newAuthenticationMode
            )
        }
    }

    private fun emailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email
            )
        }
    }

    private fun passwordChange(password: String) {
        val requirements = mutableListOf<PasswordRequirement>()
        if (password.length > 8) {
            requirements.add(PasswordRequirement.EIGHT_CHARACTERS)
        }
        if (password.any() { it.isDigit() }) {
            requirements.add(PasswordRequirement.NUMBER)
        }
        if (password.any() { it.isUpperCase() }) {
            requirements.add(PasswordRequirement.CAPITAL_LETTER)
        }
        _uiState.update {
            it.copy(
                password = password,
                passwordRequirements = requirements
            )
        }
    }

    private fun authenticate() {
        var statusFail = false;
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        if (_uiState.value.authenticationMode == AuthenticationMode.SIGN_IN) {
            var email = _uiState.value.email.toString()
            var password = _uiState.value.password.toString();
            LoginFirebase(email, password) { isSuccess ->
                if (isSuccess) {
                    // navigate to home screen
                }
                statusFail = true;
            }
        } else if (_uiState.value.authenticationMode == AuthenticationMode.SIGN_UP) {
            var email = _uiState.value.email.toString()
            var password = _uiState.value.password.toString();
            CreateUserInFirebase(email, password) { isSuccess ->
                if (isSuccess) {
                    // navigate to home screen
                }
                statusFail = true;
            }
        }

        if (statusFail == true) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(3000L)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Some thing went wrong!"
                    )
                }
            }
        }
    }

    private fun dismissError() {
        _uiState.update {
            it.copy(
                error = null
            )
        }
    }

    fun handleEvent(authenticationEvent: AuthenticationEvent) {
        when (authenticationEvent) {
            is AuthenticationEvent.ToggleAuthenticationMode -> {
                toggleAuthenticationMode()
            }

            is AuthenticationEvent.EmailChanged -> {
                emailChange(authenticationEvent.emailAddress)
            }
            is AuthenticationEvent.PasswordChanged -> {
                passwordChange(authenticationEvent.password)
            }
            is AuthenticationEvent.Authenticate -> {
                authenticate()
            }
            is AuthenticationEvent.ErrorDismissed -> {
                dismissError()
            }

        }
    }
}