package natour.developer.jumping_back_in.presentation.ui.camera.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import natour.developer.jumping_back_in.presentation.ui.camera.viewmodel.BlurViewmodel
import java.io.File
import java.util.Date

const val TAG = "PictureView"

@Composable
fun PictureView(modifier: Modifier = Modifier, context: Context = LocalContext.current,
                viewmodel: BlurViewmodel) {
    fun getUri(): Uri{
        val fileName = Date().time.toString()
        val photoFile = File.createTempFile(
            fileName, ".jpg", context.externalCacheDir
        )
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", photoFile)
        return uri
    }
    val fileName = Date().time.toString()
    val photoFile = File.createTempFile(
        fileName, ".jpg", context.externalCacheDir
    )
    var uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", photoFile)
    var imageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
            imageUri = uri
            Log.d(TAG, "imageUri: $imageUri")
        }


    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { permissionGranted ->
            if (permissionGranted) {
                Log.d(TAG, "permission granted")
                launcher.launch(uri)
            } else {
                Log.d(TAG, "permission denied")
            }
        }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                if(uri.path?.isNotEmpty() == true){
                    uri = getUri()
                }
                launcher.launch(uri)

            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }

        }, content = {
            Icon(Icons.Default.PhotoCamera, contentDescription = null)
        })
    }) {
        val options = listOf(
            BlurOption("a little blurred", 1),
            BlurOption("more blurred", 2),
            BlurOption("the most blurred", 3)
        )
        var selectedOption by remember { mutableStateOf(options[0]) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(it)
        ) {

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color.Red)

            ) {
                if (imageUri.path?.isNotEmpty() == true) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center),
                        painter = rememberAsyncImagePainter(model = imageUri),
                        contentDescription = "snapped image"
                    )
                }
            }
            BlurActions(
                options = options,
                onButtonPressed = {
                    viewmodel.applyBlur(blurLevel = 1, imageUri = imageUri)
                },
                selectedOption = selectedOption
            ) {
             selection ->
                    Log.d("RadioSelector", selection.title)
                    selectedOption = selection
                }

        }
    }
}


@Composable
fun BlurActions(modifier: Modifier = Modifier,
                options: List<BlurOption>,
                onButtonPressed: () -> Unit, selectedOption: BlurOption, onOptionSelected: (BlurOption) -> Unit){
    Text("Select blur amount:", style = MaterialTheme.typography.headlineLarge)
    MyRadioGroup(
        selectedOption = selectedOption, options = options, onOptionSelected = onOptionSelected
    )
    ElevatedButton(onClick = {
        onButtonPressed()
    }) {
        Text("Blur image")
    }
}

data class BlurOption(
    val title: String,
    val degree: Int
)
@Composable
fun MyRadioGroup(
    modifier: Modifier = Modifier,
    selectedOption: BlurOption,
    options: List<BlurOption>,
    onOptionSelected: (BlurOption) -> Unit,
) {
    Column(modifier = modifier) {
        repeat(
            options.size
        ) { index ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedOption == options[index],
                    onClick = { onOptionSelected(options[index]) })
                Text(text = options[index].title)
            }
        }
    }
}


// TODO: figure this out
//@Preview
//@Composable
//private fun PictureViewPreview() {
//    PictureView()
//}


@Preview
@Composable
private fun RadioGroupPreview() {
    MyRadioGroup(
        modifier = Modifier.fillMaxSize(), options = listOf(
        BlurOption("a little blurred", 1),
        BlurOption("more blurred", 2),
        BlurOption("the most blurred", 3),
    ), selectedOption =   BlurOption("a little blurred", 1),
    ) {

    }
}