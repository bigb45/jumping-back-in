package natour.developer.jumping_back_in.presentation.ui.components

import android.graphics.Movie
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import natour.developer.jumping_back_in.R

import natour.developer.jumping_back_in.models.MovieModel

const val COVER_IMAGE_PATH = "https://image.tmdb.org/t/p/w500"
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(modifier: Modifier = Modifier,
              movieModel: MovieModel) {
    Box(modifier = modifier
        .fillMaxWidth()
        .shadow(elevation = 5.dp, shape = RoundedCornerShape(8.dp))
        .clip(shape = RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .height(100.dp)
    ){

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()

        ) {
//            movie cover

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(12.dp))

                    .width(70.dp)
            ){

                GlideImage(

                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    contentScale = ContentScale.Crop,
                    model = "$COVER_IMAGE_PATH${movieModel.coverPath}"
                    , contentDescription = "Cover image for the movie ${movieModel.title}"

                )
            }

//            name & date
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
            ){
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()

                ){
                    Text(movieModel.title, style =  MaterialTheme.typography.bodyLarge)
                    Text(movieModel.releaseDate)

                }
                Text("${movieModel.rating}/10", style = MaterialTheme.typography.bodySmall)
//                Text("Duration: 1h 45m", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movieModel = MovieModel(
        title = "Test movie",
        description = "This is a test movie",
        rating = 4.5,
        isForAdult = false,
            coverPath = "/865DntZzOdX6rLMd405R0nFkLmL.jpg",
        releaseDate = "2024-05-23"
    )
    MovieCard(
        movieModel = movieModel
    )
}