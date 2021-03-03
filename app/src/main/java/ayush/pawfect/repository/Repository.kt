package ayush.pawfect.repository

import ayush.pawfect.model.Breed
import ayush.pawfect.model.Dog


interface Repository {

    suspend fun getDogs(
        apiKey: String,
        size: String,
        order: String,
        page: Int,
        limit: Int,
    ): List<Dog>

    suspend fun searchDogs(
        apiKey: String,
        query: String,
    ): List<Breed>


    suspend fun getDog(
        apiKey: String,
        id: String
    ): Dog?

}