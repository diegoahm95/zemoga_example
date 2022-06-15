package com.diegohurtado.zemoga.feature.list.view

import com.diegohurtado.zemoga.R
import com.diegohurtado.zemoga.core.model.entities.DeletedItem
import com.diegohurtado.zemoga.core.model.entities.Favorite
import com.diegohurtado.zemoga.core.model.entities.Post
import com.diegohurtado.zemoga.core.view.adapter.BaseAdapter
import com.diegohurtado.zemoga.databinding.ListItemBinding

class PostAdapter(
    list: List<Post>,
    private val postListener: PostListener,
    private var favorites: List<Favorite>,
    private var deleted: List<DeletedItem>
) : BaseAdapter<ListItemBinding, Post>(list) {

    override val layoutId: Int = R.layout.list_item

    override fun bind(binding: ListItemBinding, item: Post) {
        binding.apply {
            post = item
            listener = postListener
            isFavorite = favorites.find { it.postId == item.id } != null
            executePendingBindings()
        }
    }

    fun updateListData(items: List<Post>, favorites: List<Favorite>, deleted: List<DeletedItem>) {
        this.favorites = favorites
        this.deleted = deleted
        updateData(filterItems(items, deleted))
    }

    private fun filterItems(
        posts: List<Post>,
        deleted: List<DeletedItem>
    ): List<Post> {
        val newList = mutableListOf<Post>()
        posts.forEach { item ->
            val shouldBeRemoved = deleted.find { it.postId == item.id } != null
            if (!shouldBeRemoved){
                newList.add(item)
            }
        }
        return newList
    }
}