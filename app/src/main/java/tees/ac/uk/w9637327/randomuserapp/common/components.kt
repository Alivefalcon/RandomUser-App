package tees.ac.uk.w9637327.randomuserapp.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tees.ac.uk.w9637327.randomuserapp.R
import tees.ac.uk.w9637327.randomuserapp.state.authenticationstate.AuthenticationState
import tees.ac.uk.w9637327.randomuserapp.enums.AuthenticationMode
import tees.ac.uk.w9637327.randomuserapp.enums.PasswordRequirement
import tees.ac.uk.w9637327.randomuserapp.state.authenticationstate.AuthenticationEvent


@Composable
fun AuthenticationTitle(
    modifier: Modifier,
    authenticationMode: AuthenticationMode
) {
    Text(
        text = stringResource(
            id = if (authenticationMode == AuthenticationMode.SIGN_IN)
                R.string.label_sign_In
            else R.string.label_sign_Up
        ),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

}

@Composable
fun AuthenticationButton(
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean?,
    authenticate: () -> Unit
) {
    Button(onClick = { authenticate() }, enabled = enableAuthentication ?: false) {
        Text(
            text = if (authenticationMode == AuthenticationMode.SIGN_UP) {
                stringResource(id = R.string.label_sign_Up)
            } else {
                stringResource(id = R.string.label_sign_In)
            }
        )
    }
}



@Composable
fun AuthenticationErrorDialog(
    modifier: Modifier = Modifier,
    error: String,
    onDismissError: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {onDismissError() },
        confirmButton = {
            TextButton(onClick = { onDismissError() }) {
                Text(text = stringResource(id = R.string.error_button_text))
            }
        },
        title = {
            Text(text = stringResource(R.string.error_title), fontSize = 18.sp)
        },
        text = { Text(text = error) }
    )

}

@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    email: String?,
    password: String?,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onToggleAthMode: () -> Unit,
    passwordSatisfiedRequirement: List<PasswordRequirement>,
    onAuthenticate: () -> Unit,
    enableAuthentication: Boolean?
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(32.dp))
        AuthenticationTitle(modifier = modifier, authenticationMode = authenticationMode)

        Spacer(modifier = modifier.height(10.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(23.dp)
        ) {
            Column(
                modifier = modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                EmailInput(
                    email = email,
                    onEmailChanged = onEmailChanged,
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(10.dp))
                PasswordInput(
                    password = password,
                    onPasswordChanged = onPasswordChanged,
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(20.dp))
                AnimatedVisibility(visible = authenticationMode == AuthenticationMode.SIGN_UP) {
                    PasswordRequirements(satisfiedRequirements = passwordSatisfiedRequirement)
                }
                Spacer(modifier = modifier.height(10.dp))
                AuthenticationButton(
                    authenticationMode = authenticationMode,
                    enableAuthentication = enableAuthentication
                ) {
                    onAuthenticate()
                }
                Spacer(modifier = modifier.height(20.dp))

                ToggleAuthenticationMode(
                    authenticationMode = authenticationMode,
                    toggleAuthenticationAction = onToggleAthMode
                )
            }

        }
    }
}
@Composable
fun PasswordRequirements(
    satisfiedRequirements: List<PasswordRequirement>
) {
    Column(
        modifier = Modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        RequirementField(PasswordRequirement.NUMBER,satisfiedRequirements)
        RequirementField(PasswordRequirement.CAPITAL_LETTER,satisfiedRequirements)
        RequirementField(PasswordRequirement.EIGHT_CHARACTERS,satisfiedRequirements)
    }
}

@Composable
fun RequirementField(
    passwordRequirement: PasswordRequirement,
    satisfiedRequirements: List<PasswordRequirement>
) {

    var color: Color = Color.LightGray
    if (passwordRequirement in satisfiedRequirements) {
        color = Color.Green
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Check, contentDescription = "check mark",
            modifier = Modifier.size(12.dp),
            tint = color
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = passwordRequirement.label),
            fontWeight = FontWeight.Light,
            color = color,
            fontSize = 12.sp,
            textAlign = TextAlign.Start
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    email: String?,
    onEmailChanged: (String) -> Unit
) {
    TextField(
        value = email ?: "",
        onValueChange = {
            onEmailChanged(it)
        },
        label = {
            Text(
                text = stringResource(id = R.string.label_email)
            )
        },
        singleLine = true,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "email icon" ) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done )

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String?,
    onPasswordChanged: (String) -> Unit
) {
    var isPasswordHidden by remember { mutableStateOf(true) }
    TextField(
        value = password ?: "",
        onValueChange = {
            onPasswordChanged(it)
        },
        visualTransformation =
        if (isPasswordHidden) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
        ,
        label = {
            Text(
                text = stringResource(id = R.string.label_password)
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "password icon"
            )
        },
        trailingIcon = {
            Icon(
                imageVector = if (isPasswordHidden) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                contentDescription = "password visibility",
                modifier = Modifier.clickable(
                    onClickLabel = if (isPasswordHidden) {
                        stringResource(id = R.string.show_password)
                    } else {
                        stringResource(
                            id = R.string.hide_password
                        )
                    }
                ) {
                    isPasswordHidden = !isPasswordHidden
                }
            )
        },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password )
    )
}


@Composable
fun ToggleAuthenticationMode(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    toggleAuthenticationAction: () -> Unit
) {

    Surface(
        modifier = modifier.padding(16.dp)
    ){
        TextButton(
            modifier= Modifier.background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(8.dp),
            onClick = { toggleAuthenticationAction() }
        ) {
            Text(
                text = stringResource(
                    id = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                        R.string.action_need_account
                    }else {
                        R.string.action_already_have_an_account
                    }
                ),
                fontSize = 13.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(backClicked: () -> Unit, logoutClicked: () -> Unit) {

    TopAppBar(
        title = {
            Text(
                text = "Random User App", color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                backClicked()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

        },
        actions = {
            IconButton(onClick = {
                logoutClicked()
            }) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = "Logout",
                )
            }
        }
    )
}