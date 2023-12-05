package tees.ac.uk.w9637327.randomuserapp.state.authenticationstate

import tees.ac.uk.w9637327.randomuserapp.enums.AuthenticationMode
import tees.ac.uk.w9637327.randomuserapp.enums.PasswordRequirement

data class AuthenticationState(
    val email: String? = null,
    val password: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val passwordRequirements: List<PasswordRequirement> = emptyList(),
    val authenticationMode: AuthenticationMode = AuthenticationMode.SIGN_IN
) {
    fun isFormValid(): Boolean? {
        return password?.isNotEmpty() == true
                && email?.isNotEmpty() == true
                && (authenticationMode== AuthenticationMode.SIGN_IN
                || passwordRequirements.containsAll(PasswordRequirement.values().toList()))
    }
}