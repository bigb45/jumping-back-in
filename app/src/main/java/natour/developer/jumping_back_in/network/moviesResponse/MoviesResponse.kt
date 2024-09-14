package natour.developer.jumping_back_in.network.moviesResponse

import com.google.gson.annotations.SerializedName
import natour.developer.jumping_back_in.models.MovieModel

data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieModel>
)
