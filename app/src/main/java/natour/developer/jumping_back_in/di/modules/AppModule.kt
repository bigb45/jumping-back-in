package natour.developer.jumping_back_in.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import natour.developer.jumping_back_in.data.MoviesRepository
import natour.developer.jumping_back_in.data.MoviesRepositoryImpl
import natour.developer.jumping_back_in.data.WorkerRepository
import natour.developer.jumping_back_in.data.WorkerRepositoryImpl
import natour.developer.jumping_back_in.network.MoviesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


const val BASE_URL = "https://api.themoviedb.org/3/"
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideMoviesService(): MoviesService{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesService::class.java)


    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesService: MoviesService
    ): MoviesRepository = MoviesRepositoryImpl(
        moviesService = moviesService
    )

    @Provides
    @Singleton
    fun provideWorkerRepository(@ApplicationContext context: Context): WorkerRepository{
        return WorkerRepositoryImpl(context = context)

    }

}