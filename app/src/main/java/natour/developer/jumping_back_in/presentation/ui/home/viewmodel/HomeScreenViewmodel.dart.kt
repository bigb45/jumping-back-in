package natour.developer.jumping_back_in.presentation.ui.home.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import natour.developer.jumping_back_in.data.MoviesRepository
import natour.developer.jumping_back_in.data.MoviesRepositoryImpl
import natour.developer.jumping_back_in.models.MovieModel
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewmodel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {
    private val _movies = MutableStateFlow<List<MovieModel>>(emptyList())
    private val _error = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow<Boolean>(false)

    val movies: StateFlow<List<MovieModel>> = _movies
    val error: StateFlow<String?> = _error
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchMovies() = viewModelScope.launch {
        try{
            _isLoading.value = true
            val movies = moviesRepository.getMovies()
            _movies.value = movies
        } catch (e: Exception){
            _error.value = e.message
        }finally {
            _isLoading.value = false
        }
    }
}