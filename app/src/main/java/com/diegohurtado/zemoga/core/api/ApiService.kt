package com.diegohurtado.zemoga.core.api

import com.diegohurtado.zemoga.core.model.entities.Comment
import com.diegohurtado.zemoga.core.model.entities.Post
import com.diegohurtado.zemoga.core.model.entities.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("users/{id}/")
    suspend fun getUser(
        @Path("id") userId: String
    ): User

    @GET("posts/{id}/comments")
    suspend fun getPostComments(
        @Path("id") postId: String
    ): List<Comment>

}