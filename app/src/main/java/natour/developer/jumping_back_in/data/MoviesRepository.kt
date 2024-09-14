package natour.developer.jumping_back_in.data

import android.util.Log
import natour.developer.jumping_back_in.models.MovieModel
import natour.developer.jumping_back_in.network.MoviesService
import natour.developer.jumping_back_in.utils.safeApiCall
import javax.inject.Inject

interface MoviesRepository {
    suspend fun getMovies(): List<MovieModel>
}

class MoviesRepositoryImpl
    @Inject constructor(
    private val moviesService: MoviesService
): MoviesRepository {
    override suspend fun getMovies(): List<MovieModel> {
        val safeResult = safeApiCall {
            moviesService.getMovies()
        }
        Log.e("MoviesRepositoryImpl", "getMovies: $safeResult")
        return when  {
            safeResult.isSuccess -> safeResult.getOrThrow().results
            safeResult.isFailure -> throw safeResult.exceptionOrNull() ?: Exception("An undefined error occurred")
            else -> throw Exception("An undefined error occurred")
        }
    }


}

