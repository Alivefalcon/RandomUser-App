package tees.ac.uk.w9637327.randomuserapp.randomuserapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.Crossfade
import androidx.lifecycle.viewmodel.compose.viewModel
import tees.ac.uk.w9637327.randomuserapp.navigations.AppRouter
import tees.ac.uk.w9637327.randomuserapp.navigations.RandomUserAppNavigation
import tees.ac.uk.w9637327.randomuserapp.screens.Authenticationscreen
import tees.ac.uk.w9637327.randomuserapp.screens.MainScreen
import tees.ac.uk.w9637327.randomuserapp.viewmodels.AuthenticationViewModel
import tees.ac.uk.w9637327.randomuserapp.viewmodels.UserViewModel

@Composable
fun RandomUserApp()
{
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is RandomUserAppNavigation.AuthenticationScreen -> {
                    val viewModel: AuthenticationViewModel = viewModel()
                    MaterialTheme() {
                        Authenticationscreen(
                            modifier = Modifier.fillMaxWidth(),
                            authenticationState = viewModel.uiState.collectAsState().value,
                            handleEvent = viewModel::handleEvent
                        )
                    }
                }
                is RandomUserAppNavigation.MainScreen -> {
                    val viewModel: UserViewModel = viewModel()
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    RandomUserApp()
}