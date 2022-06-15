package com.diegohurtado.zemoga.core.repository.post.datasources

import com.diegohurtado.zemoga.core.api.ApiService
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPosts() =
        apiService.getPosts()

    suspend fun getComments(postId: String) =
        apiService.getPostComments(postId)
}