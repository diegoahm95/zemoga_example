package com.diegohurtado.zemoga.core

import com.diegohurtado.zemoga.core.model.entities.DeletedItem
import com.diegohurtado.zemoga.core.model.entities.Post

fun List<Post>.toDeleted(): List<DeletedItem> {
    val deleted = mutableListOf<DeletedItem>()
    forEach { post ->
        deleted.add(DeletedItem(post))
    }
    return deleted
}