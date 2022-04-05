package com.example.spaceflightnews.di


import android.app.Application
import androidx.room.Room
import com.example.spaceflightnews.db.NewsDao
import com.example.spaceflightnews.db.NewsDatabase
import com.example.network.NewsApi
import com.example.network.RemoteToDomainMapper
import com.example.spaceflightnews.repository.NewsRepository
import com.example.spaceflightnews.repository.NewsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This is a module class which is responsible for the injection of retrofit,database,repository and
 * mappers
 */
private const val BASE_URL = "https://api.spaceflightnewsapi.net/"
private const val databaseName = "news_database"

@Module
@InstallIn(SingletonComponent::class)
class   AppModule {

   @Singleton
   @Provides
   fun provideNewsApi(): NewsApi = Retrofit.Builder()
   .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(NewsApi::class.java)


   @Provides
   @Singleton
   fun provideDatabaseDao(app:Application):NewsDao =
      Room.databaseBuilder(app,NewsDatabase::class.java, databaseName)
         .build().getNewsDao()


   @Provides
   @Singleton
   fun providerEntityMapper(): RemoteToDomainMapper =
       RemoteToDomainMapper()

   @Singleton
   @Provides
   fun provideDefaultNewsAppRepository(
      newsApi: NewsApi,
      newsDao:NewsDao ,
      mapper: RemoteToDomainMapper
   ): NewsRepository = NewsRepositoryImp(newsApi,newsDao,mapper)

}