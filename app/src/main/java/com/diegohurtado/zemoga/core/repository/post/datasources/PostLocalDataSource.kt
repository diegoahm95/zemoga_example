package com.diegohurtado.zemoga.core.repository.post.datasources

import com.diegohurtado.zemoga.core.model.daos.DeletedDao
import com.diegohurtado.zemoga.core.model.daos.FavoriteDao
import com.diegohurtado.zemoga.core.model.entities.DeletedItem
import com.diegohurtado.zemoga.core.model.entities.Favorite
import com.diegohurtado.zemoga.core.model.entities.Post
import javax.inject.Inject

class PostLocalDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val deletedDao: DeletedDao
) {

    fun getPosts(): List<Post> =
        mutableListOf()

    suspend fun getFavorites(): List<Favorite> =
        favoriteDao.getAll()

    suspend fun createFavorite(favorite: Favorite) =
        favoriteDao.insert(favorite)

    suspend fun deleteFavorite(favorite: Favorite) =
        favoriteDao.delete(favorite)

    suspend fun isFavorite(post: Post): Boolean =
        favoriteDao.getSingle(post.id.toString()) != null

    suspend fun getDeleted(): List<DeletedItem> =
        deletedDao.getAll()

    suspend fun createDeleted(item: DeletedItem) =
        deletedDao.insert(item)

    suspend fun clearDeleted() =
        deletedDao.clear()

    suspend fun removeAll(list: List<DeletedItem>) =
        deletedDao.insertAll(list)

}