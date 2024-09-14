package natour.developer.jumping_back_in

import android.graphics.Picture
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import natour.developer.jumping_back_in.navigation.MovieNavigation
import natour.developer.jumping_back_in.presentation.ui.camera.screen.PictureView


import natour.developer.jumping_back_in.ui.theme.JumpingBackInTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JumpingBackInTheme {
               MovieNavigation()
            }
        }
    }
}

