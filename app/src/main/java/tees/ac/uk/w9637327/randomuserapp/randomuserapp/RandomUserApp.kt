package tees.ac.uk.w9637327.randomuserapp.randomuserapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import tees.ac.uk.w9637327.randomuserapp.common.AuthenticationContent
import tees.ac.uk.w9637327.randomuserapp.viewmodels.AuthenticationViewModel


@Composable
fun RandomUserApp() {
    val viewModel: AuthenticationViewModel = viewModel()
    MaterialTheme() {
        AuthenticationContent(
            modifier = Modifier.fillMaxWidth(),
            authenticationState = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    RandomUserApp()
}