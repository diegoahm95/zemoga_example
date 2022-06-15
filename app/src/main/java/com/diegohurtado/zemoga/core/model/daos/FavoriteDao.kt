package com.diegohurtado.zemoga.core.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.diegohurtado.zemoga.core.model.entities.Favorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    suspend fun getAll(): List<Favorite>

    @Query("SELECT * FROM favorite WHERE postId=:id")
    suspend fun getSingle(id: String): Favorite?

    @Insert
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)
}