package com.diegohurtado.zemoga.feature.list.view

import android.view.Menu
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.diegohurtado.zemoga.R
import com.diegohurtado.zemoga.core.BaseApplication.Companion.getAppComponent
import com.diegohurtado.zemoga.core.model.entities.Post
import com.diegohurtado.zemoga.core.view.BaseFragment
import com.diegohurtado.zemoga.core.view.ListLoader
import com.diegohurtado.zemoga.feature.list.viewmodel.ListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class ListFragment : BaseFragment(), ListLoader<Post>, PostListener {

    private val viewModel: ListViewModel by viewModels()
    override val layoutId = R.layout.fragment_list
    private lateinit var list: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var clearButton: FloatingActionButton
    private lateinit var removeAllButton: FloatingActionButton
    override fun bindViewModel() {
        getAppComponent().inject(viewModel)
        viewModel.getPosts()
    }

    override fun bindViews() {
        view?.run {
            list = findViewById(R.id.list)
            swipeLayout = findViewById<SwipeRefreshLayout>(R.id.swipe).also {
                it.setOnRefreshListener {
                    viewModel.getPosts()
                }
            }
            clearButton = findViewById<FloatingActionButton>(R.id.clear).also {
                it.setOnClickListener {
                    viewModel.restoreAll()
                }
            }
            removeAllButton = findViewById<FloatingActionButton>(R.id.remove_all).also {
                it.setOnClickListener {
                    viewModel.removeAll()
                }
            }
        }
    }

    override fun bindObservers() {
        observe(viewModel.state){
            setItems(it.items)
            setLoading(it.isLoading)
        }
    }

    override fun setItems(items: List<Post>){
        list.apply {
            if (adapter == null){
                lifecycleScope.launch {
                    adapter = PostAdapter(
                        items,
                        this@ListFragment,
                        viewModel.getFavorites(),
                        viewModel.getDeletedItems()
                    )
                }
            } else {
                (adapter as PostAdapter).updateListData(
                    items,
                    viewModel.getFavorites(),
                    viewModel.getDeletedItems()
                )

            }
        }
    }

    override fun setLoading(isLoading: Boolean){
        swipeLayout.isRefreshing = isLoading
    }

    override fun onPostClicked(post: Post) =
        findNavController().navigate(ListFragmentDirections.listToDetails(post))

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.delete).isVisible = false
        menu.findItem(R.id.favorite).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

}