package com.diegohurtado.zemoga.core.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.diegohurtado.zemoga.core.model.entities.DeletedItem

@Dao
interface DeletedDao {
    @Query("SELECT * FROM deleted")
    suspend fun getAll(): List<DeletedItem>

    @Insert
    suspend fun insert(item: DeletedItem)

    @Insert
    suspend fun insertAll(items: List<DeletedItem>)

    @Delete
    suspend fun delete(item: DeletedItem)

    @Query("DELETE FROM deleted")
    suspend fun clear()

    @Query("SELECT * FROM deleted WHERE postId=:id")
    suspend fun getSingle(id: String): DeletedItem?
}