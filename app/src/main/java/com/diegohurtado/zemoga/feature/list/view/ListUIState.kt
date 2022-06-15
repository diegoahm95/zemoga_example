package com.diegohurtado.zemoga.feature.list.view

import com.diegohurtado.zemoga.core.model.entities.DeletedItem
import com.diegohurtado.zemoga.core.model.entities.Favorite
import com.diegohurtado.zemoga.core.model.entities.Post

data class ListUIState(
    var isLoading: Boolean = false,
    val items: MutableList<Post> = mutableListOf(),
    val favorites: List<Favorite> = mutableListOf(),
    val deleted: List<DeletedItem> = mutableListOf()
)
