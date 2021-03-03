package ayush.pawfect.di

import ayush.pawfect.network.DogApi
import ayush.pawfect.repository.Repository
import ayush.pawfect.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        dogApi: DogApi
    ): Repository {
        return RepositoryImpl(dogApi)
    }
}