package natour.developer.jumping_back_in.presentation.ui.details.screen

import android.telecom.Call.Details
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Details(modifier: Modifier = Modifier, movieId: String) {

    Scaffold (modifier = modifier) {

        innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()

        ){
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "This is the movie with the ID $movieId")
        }
    }
}