package com.diegohurtado.zemoga.core.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diegohurtado.zemoga.core.model.daos.DeletedDao
import com.diegohurtado.zemoga.core.model.daos.FavoriteDao
import com.diegohurtado.zemoga.core.model.entities.DeletedItem
import com.diegohurtado.zemoga.core.model.entities.Favorite

@Database(entities = [Favorite::class, DeletedItem::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun deletedDao(): DeletedDao
}

const val DATABASE_NAME = "app_database"