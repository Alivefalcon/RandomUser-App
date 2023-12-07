package tees.ac.uk.w9637327.randomuserapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import tees.ac.uk.w9637327.randomuserapp.common.Toolbar
import tees.ac.uk.w9637327.randomuserapp.model.User
import tees.ac.uk.w9637327.randomuserapp.navigations.AppRouter
import tees.ac.uk.w9637327.randomuserapp.navigations.RandomUserAppNavigation
import tees.ac.uk.w9637327.randomuserapp.viewmodels.UserViewModel

@Composable
fun MainScreen(viewModel: UserViewModel) {
    Column {
        // Toolbar at the top
        Toolbar(isBackClickEnable = false,backClicked = {}, logoutClicked = {AppRouter.navigateTo(RandomUserAppNavigation.AuthenticationScreen)})
        // LazyColumn below the Toolbar
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(viewModel._userDataList.value) { user ->
                    UserRow(
                        user = user,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

//List Item view
@Composable
fun UserRow(user: User, viewModel: UserViewModel) {
    Card(
        modifier = Modifier
            .padding(5.dp, 4.dp, 5.dp, 4.dp)
            .fillMaxWidth()
            .clickable(indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() }) {
                viewModel.addUser(user)
                AppRouter.navigateTo(RandomUserAppNavigation.RandomUserDetailsScreen)
            },
        shape = RoundedCornerShape(CornerSize(5.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = user.picture.large),
                contentDescription = "image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(CornerSize(10.dp)))
            )

            Column(
                modifier = Modifier
                    .padding(10.dp, 5.dp, 0.dp, 5.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = user.name.toString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = user.email,
                    maxLines = 1,
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = user.phone,
                    maxLines = 1,
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}