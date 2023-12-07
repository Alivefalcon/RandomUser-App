package tees.ac.uk.w9637327.randomuserapp.navigations

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class RandomUserAppNavigation{
    object AuthenticationScreen : RandomUserAppNavigation()
    object MainScreen : RandomUserAppNavigation()
    object  RandomUserDetailsScreen : RandomUserAppNavigation()
}

object AppRouter {

    var currentScreen: MutableState<RandomUserAppNavigation> = mutableStateOf(RandomUserAppNavigation.MainScreen)

    fun navigateTo(destination : RandomUserAppNavigation){
        currentScreen.value = destination
    }


}