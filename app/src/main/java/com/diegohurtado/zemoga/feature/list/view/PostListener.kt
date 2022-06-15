package com.diegohurtado.zemoga.feature.list.view

import com.diegohurtado.zemoga.core.model.entities.Post

interface PostListener {
    fun onPostClicked(post: Post)
}