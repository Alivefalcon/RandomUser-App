package tees.ac.uk.w9637327.randomuserapp.state.authenticationstate

sealed class AuthenticationEvent {
    object ToggleAuthenticationMode: AuthenticationEvent()
    class EmailChanged(val emailAddress: String): AuthenticationEvent()
    class PasswordChanged(val password: String): AuthenticationEvent()
    object Authenticate: AuthenticationEvent()
    object ErrorDismissed: AuthenticationEvent()
}