package natour.developer.jumping_back_in.presentation.ui.home.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import natour.developer.jumping_back_in.presentation.ui.components.MovieCard
import natour.developer.jumping_back_in.presentation.ui.home.viewmodel.HomeScreenViewmodel
import natour.developer.jumping_back_in.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewmodel: HomeScreenViewmodel,
    onMovieClicked: (String) -> Unit,
    onNavigateToCamera: () -> Unit,
) {

//    val refreshState = rememberPullToRefreshState()
    LaunchedEffect(true) {
        viewmodel.fetchMovies()
    }
    val movies = viewmodel.movies.collectAsStateWithLifecycle()
    Scaffold(modifier = modifier) { innerPadding ->

        if (viewmodel.isLoading.collectAsStateWithLifecycle().value) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            Box {
                Column(

                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier

                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(PaddingValues(horizontal = 10.dp)),
                ) {
                    var query by remember { mutableStateOf("") }
                    OutlinedTextField(

                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = null)
                        },
                        placeholder = {
                            Text(
                                "Search for a movie or an actor name",
                                style = AppTypography.bodyMedium
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        value = query,
                        onValueChange = {
//                TODO: filter results through viewmodel
                                newQuery: String ->
                            query = newQuery
                        })
                    ElevatedButton(onClick = { viewmodel.fetchMovies() }) {
                        Text("Fetch movies")
                    }
                    ElevatedButton(onClick = { onNavigateToCamera() }) {
                        Text("Fetch movies")
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(movies.value.size) { index ->
                            Box (modifier = Modifier
                                .clickable {
                                    onMovieClicked(movies.value[index].title)

                                }) {
                                MovieCard(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    movieModel = movies.value[index],
                                )
                            }
                        }
                    }


                }
            }
        }
    }
}

