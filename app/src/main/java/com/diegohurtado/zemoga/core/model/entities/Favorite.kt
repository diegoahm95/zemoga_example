package com.diegohurtado.zemoga.core.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diegohurtado.zemoga.core.model.FAVORITE_TABLE_NAME

@Entity(tableName = FAVORITE_TABLE_NAME)
data class Favorite(
    @PrimaryKey
    val postId: Int
) {
    constructor(post: Post): this(post.id)
}
