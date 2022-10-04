package com.lyft.android.ohmymovies.di

import android.content.Context
import androidx.room.Room
import com.lyft.android.ohmymovies.data.local.MoviesDatabase
import com.lyft.android.ohmymovies.data.local.dao.MoviesDao
import com.lyft.android.ohmymovies.data.local.dao.SectionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MoviesDatabase::class.java,
            "movies.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao
    }

    @Provides
    @Singleton
    fun provideSectionDao(database: MoviesDatabase): SectionDao {
        return database.sectionDao
    }
}