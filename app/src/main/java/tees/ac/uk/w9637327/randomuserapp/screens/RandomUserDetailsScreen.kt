package tees.ac.uk.w9637327.randomuserapp.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import tees.ac.uk.w9637327.randomuserapp.common.Toolbar
import tees.ac.uk.w9637327.randomuserapp.model.User
import tees.ac.uk.w9637327.randomuserapp.navigations.AppRouter
import tees.ac.uk.w9637327.randomuserapp.navigations.RandomUserAppNavigation
import tees.ac.uk.w9637327.randomuserapp.viewmodels.UserViewModel

@Composable
fun RandomUserDetailsScreen(viewModel: UserViewModel) {
    Column {
        Toolbar(
            isBackClickEnable = true,
            backClicked = {AppRouter.navigateTo(RandomUserAppNavigation.MainScreen)},
            logoutClicked = { AppRouter.navigateTo(RandomUserAppNavigation.AuthenticationScreen) })
        // LazyColumn below the Toolbar
        viewModel.user?.let {
            UserDetailsScreen(
                user = it
            )
        }
    }
}

@Composable
fun UserDetailsScreen(user: User) {
    Box(
        modifier = Modifier
            .padding(8.dp, 4.dp, 8.dp, 4.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = user.picture.large),
                contentDescription = "image",
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(CornerSize(10.dp)))
            )

            Text(
                text = "Name: " + user.name.toString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
            )

            Text(
                text = "Email: " + user.email,
                modifier = Modifier.padding(0.dp, 0.dp, 4.dp, 0.dp),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Phone No: " + user.phone,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Gender: " + user.gender,
                style = MaterialTheme.typography.bodySmall,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        AppRouter.navigateTo(RandomUserAppNavigation.MainScreen)
                    },
            ) {
                Text(
                    text = "Address: ",
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = user.location.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    softWrap = true
                )
            }

        }
    }
}