package natour.developer.jumping_back_in.network

import natour.developer.jumping_back_in.models.MovieModel
import natour.developer.jumping_back_in.network.moviesResponse.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers

const val bearerToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNGMyNDJiYTYwYzQzY2EzNmE0ODg1NGI1Mjk1NjU4ZSIsIm5iZiI6MTcyNjEzNjQyNS4xOTQyOTUsInN1YiI6IjY2ZTJiZThlMDAwMDAwMDAwMGI5MWE2OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.I8c8Y24sUe4_InC48vOFM1ZdO_hGa2cCTya4tHnUJtg"
interface MoviesService {
    @Headers("Authorization: Bearer $bearerToken")
    @GET("discover/movie")
    suspend fun getMovies(): MoviesResponse

}