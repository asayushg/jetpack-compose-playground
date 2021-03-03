package ayush.pawfect.repository

import android.util.Log
import ayush.pawfect.model.Breed
import ayush.pawfect.model.Dog
import ayush.pawfect.network.DogApi
import ayush.pawfect.util.Constants.TAG

class RepositoryImpl(
    private val dogApi: DogApi
) : Repository {
    override suspend fun getDogs(
        apiKey: String,
        size: String,
        order: String,
        page: Int,
        limit: Int,
    ): List<Dog> {

        var dogs = listOf<Dog>()
        try {
            dogs = dogApi.getDogs(
                apiKey = apiKey,
                order = order,
                size = size,
                page = page,
                limit = limit,
            )

        } catch (e: Exception) {
            Log.d(TAG, "getDogs: $e")
        }
        return dogs

    }

    override suspend fun searchDogs(apiKey: String, query: String): List<Breed> {

        var dogs = listOf<Breed>()
        try {
            dogs = dogApi.searchDogs(
                apiKey = apiKey,
                query = query,
            )

        } catch (e: Exception) {
            Log.d(TAG, "searchDogs: $e")
        }
        return dogs

    }

    override suspend fun getDog(apiKey: String, id: String): Dog? {
        var dog :Dog? = null
        try {
            dog = dogApi.getDog(
                apiKey = apiKey,
                imageId = id,
            )

        } catch (e: Exception) {
            Log.d(TAG, "getdog: $e")
        }
        return dog
    }

}