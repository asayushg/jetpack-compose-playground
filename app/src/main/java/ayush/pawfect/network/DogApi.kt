package ayush.pawfect.network

import ayush.pawfect.model.Breed
import ayush.pawfect.model.Dog
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface DogApi {

    @GET("images/search")
    suspend fun getDogs(
        @Header("x-api-key") apiKey: String,
        @Query("size") size: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<Dog>

    @GET("breeds/search")
    suspend fun searchDogs(
        @Header("x-api-key") apiKey: String,
        @Query("q") query: String,
    ): List<Breed>

    @GET("images/{image_id}")
    suspend fun getDog(
        @Header("x-api-key") apiKey: String,
        @Path("image_id") imageId: String,
    ): Dog

}