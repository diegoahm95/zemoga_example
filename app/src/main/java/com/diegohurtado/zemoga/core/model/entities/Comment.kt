package com.diegohurtado.zemoga.core.model.entities

import com.diegohurtado.zemoga.core.view.adapter.ListAdapterItem

data class Comment(
    val postId: Int,
    override val id: Int,
    val body: String
): ListAdapterItem
