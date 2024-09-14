package natour.developer.jumping_back_in.models

import com.google.gson.annotations.SerializedName


data class MovieModel(
   @SerializedName("adult")
    val isForAdult: Boolean,
    @SerializedName("backdrop_path")
   val coverPath: String,
   @SerializedName("original_title")
    val title: String,
   @SerializedName("overview")
    val description: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("release_date")
    val releaseDate: String,

)


//{
//    "adult": false,
//    "backdrop_path": "/mKOBdgaEFguADkJhfFslY7TYxIh.jpg",
//    "genre_ids": [
//    28,
//    878,
//    35,
//    12,
//    53
//    ],
//    "id": 365177,
//    "original_language": "en",
//    "original_title": "Borderlands",
//    "overview": "Returning to her home planet, an infamous bounty hunter forms an unexpected alliance with a team of unlikely heroes. Together, they battle monsters and dangerous bandits to protect a young girl who holds the key to unimaginable power.",
//    "popularity": 2388.352,
//    "poster_path": "/865DntZzOdX6rLMd405R0nFkLmL.jpg",
//    "release_date": "2024-08-07",
//    "title": "Borderlands",
//    "video": false,
//    "vote_average": 5.849,
//    "vote_count": 445
//},