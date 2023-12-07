package tees.ac.uk.w9637327.randomuserapp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tees.ac.uk.w9637327.randomuserapp.model.User
import tees.ac.uk.w9637327.randomuserapp.model.UserModel
import tees.ac.uk.w9637327.randomuserapp.network.RandomService
import tees.ac.uk.w9637327.randomuserapp.network.networkrequest

class UserViewModel() : ViewModel() {
    var _userData = mutableStateOf<List<User>>(emptyList())
    var isLoading = mutableStateOf(true)
    init {
        getRandomUsers();
        isLoading.value = false;
    }
    private fun getRandomUsers() {
        var randomService = RandomService()
        viewModelScope.launch {
            _userData.value = randomService.getRandomUsers().results;
        }
    }
}