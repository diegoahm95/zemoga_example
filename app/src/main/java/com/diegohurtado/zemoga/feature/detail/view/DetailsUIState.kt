package com.diegohurtado.zemoga.feature.detail.view

import com.diegohurtado.zemoga.core.model.entities.Comment
import com.diegohurtado.zemoga.core.model.entities.Post
import com.diegohurtado.zemoga.core.model.entities.User

data class DetailsUIState(
    val post: Post,
    val comments: List<Comment>,
    val user: User,
    val isLoading: Boolean,
    val isFavorite: Boolean,
    val deleted: Boolean
)
