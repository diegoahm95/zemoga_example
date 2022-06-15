package com.diegohurtado.zemoga.feature.detail.view

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.diegohurtado.zemoga.R
import com.diegohurtado.zemoga.core.BaseApplication.Companion.getAppComponent
import com.diegohurtado.zemoga.core.model.entities.Comment
import com.diegohurtado.zemoga.core.model.entities.Post
import com.diegohurtado.zemoga.core.model.entities.User
import com.diegohurtado.zemoga.core.view.BaseFragment
import com.diegohurtado.zemoga.core.view.ListLoader
import com.diegohurtado.zemoga.feature.detail.viewmodel.DetailsViewModel

class DetailsFragment: BaseFragment(), ListLoader<Comment> {
    override val layoutId: Int = R.layout.fragment_details
    val args: DetailsFragmentArgs by navArgs()
    val viewModel: DetailsViewModel by viewModels()
    private lateinit var list: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var author: TextView

    override fun bindViewModel() {
        getAppComponent().inject(viewModel)
        viewModel.setPost(args.post)
        viewModel.getExtraInfo()
    }

    override fun bindObservers() {
        observe(viewModel.state){
            if (it.deleted){
                closeFragment()
            }
            setItems(it.comments)
            setLoading(it.isLoading)
            setDetails(it.post)
            setAuthor(it.user)
            invalidateMenu()
        }
    }

    private fun closeFragment() =
        findNavController().navigateUp()

    override fun bindViews() {
        view?.run {
            list = findViewById(R.id.list)
            progress = findViewById(R.id.progress)
            title = findViewById(R.id.title)
            description = findViewById(R.id.description)
            author = findViewById(R.id.author)
        }
    }

    private fun setDetails(post: Post){
        title.text = post.title
        description.text = post.body
    }

    private fun setAuthor(user: User){
        author.text = getString(R.string.author_placeholder, user.name, user.email, user.phone)
    }

    override fun setItems(items: List<Comment>) {
        list.apply {
            if (adapter == null){
                adapter = CommentAdapter(items)
            } else {
                (adapter as CommentAdapter).updateData(items)
            }
        }
    }

    override fun setLoading(isLoading: Boolean) {
        progress.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.delete).isVisible = true
        menu.findItem(R.id.favorite).isVisible = true
        val fill = viewModel.state.value?.isFavorite ?: false
        menu.findItem(R.id.favorite).apply {
            if (fill){
                setIcon(R.drawable.ic_favorite)
            } else {
                setIcon(R.drawable.ic_favorite_empty)
            }
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                viewModel.toggleFavorite()
            }
            R.id.delete -> {
                viewModel.deleteItem()
            }
        }
        return false
    }

    private fun invalidateMenu(){
        activity?.invalidateOptionsMenu()
    }

}