package com.diegohurtado.zemoga.core.repository.post

import com.diegohurtado.zemoga.core.model.entities.Comment
import com.diegohurtado.zemoga.core.model.entities.DeletedItem
import com.diegohurtado.zemoga.core.model.entities.Favorite
import com.diegohurtado.zemoga.core.model.entities.Post
import com.diegohurtado.zemoga.core.repository.post.datasources.PostLocalDataSource
import com.diegohurtado.zemoga.core.repository.post.datasources.PostRemoteDataSource
import com.diegohurtado.zemoga.core.toDeleted
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val remoteDataSource: PostRemoteDataSource,
    private val localDataSource: PostLocalDataSource
) {

    suspend fun getPosts(): List<Post> {
        return localDataSource.getPosts().ifEmpty {
            remoteDataSource.getPosts()
        }
    }

    suspend fun getPostComments(postId: String): List<Comment> =
        remoteDataSource.getComments(postId)

    suspend fun toggleFavorite(post: Post): Boolean {
        val favorite = Favorite(post)
        var isFavorite = false
        localDataSource.run {
            if (isFavorite(post)){
                deleteFavorite(favorite)
            } else {
                createFavorite(favorite)
                isFavorite = true
            }
        }
        return isFavorite
    }

    suspend fun isFavorite(post: Post) =
        localDataSource.isFavorite(post)

    suspend fun getFavorites() =
        localDataSource.getFavorites()

    suspend fun getDeleted() =
        localDataSource.getDeleted()

    suspend fun deletePost(post: Post) =
        localDataSource.createDeleted(DeletedItem(post))

    suspend fun restore() =
        localDataSource.clearDeleted()

    suspend fun removeAll(items: List<Post>) {
        restore()
        localDataSource.removeAll(items.toDeleted())
    }

}