package com.diegohurtado.zemoga.core.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diegohurtado.zemoga.core.model.DELETED_TABLE_NAME

@Entity(tableName = DELETED_TABLE_NAME)
data class DeletedItem(
    @PrimaryKey
    val postId: Int
) {
    constructor(post: Post): this(post.id)
}
