package ayush.pawfect.di

import ayush.pawfect.network.DogApi
import ayush.pawfect.util.Constants.API_KEY
import ayush.pawfect.util.Constants.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create())
            ).build()
    }

    @Singleton
    @Provides
    fun provideRecipeApi(retrofit: Retrofit): DogApi {
        return retrofit.create(DogApi::class.java)
    }

    @Singleton
    @Provides
    fun provideApiKey(): String {
        return API_KEY
    }


}