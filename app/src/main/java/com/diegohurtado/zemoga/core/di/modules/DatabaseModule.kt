package com.diegohurtado.zemoga.core.di.modules

import android.content.Context
import androidx.room.Room
import com.diegohurtado.zemoga.core.model.AppDatabase
import com.diegohurtado.zemoga.core.model.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideFavoriteDao(db: AppDatabase) =
        db.favoriteDao()

    @Singleton
    @Provides
    fun provideDeletedDao(db: AppDatabase) =
        db.deletedDao()
}