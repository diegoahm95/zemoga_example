package com.diegohurtado.zemoga.feature.detail.view

import com.diegohurtado.zemoga.R
import com.diegohurtado.zemoga.core.model.entities.Comment
import com.diegohurtado.zemoga.core.view.adapter.BaseAdapter
import com.diegohurtado.zemoga.databinding.CommentItemBinding

class CommentAdapter(
    list: List<Comment>
) : BaseAdapter<CommentItemBinding, Comment>(list) {

    override val layoutId: Int = R.layout.comment_item

    override fun bind(binding: CommentItemBinding, item: Comment) {
        binding.apply {
            comment = item
            executePendingBindings()
        }
    }
}