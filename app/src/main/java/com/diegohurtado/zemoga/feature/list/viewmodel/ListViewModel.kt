package com.diegohurtado.zemoga.feature.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegohurtado.zemoga.core.model.entities.DeletedItem
import com.diegohurtado.zemoga.core.model.entities.Favorite
import com.diegohurtado.zemoga.core.model.entities.Post
import com.diegohurtado.zemoga.core.repository.post.PostRepository
import com.diegohurtado.zemoga.feature.list.view.ListUIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var repository: PostRepository
    val state: MutableLiveData<ListUIState> =
        MutableLiveData<ListUIState>().apply {
            value = ListUIState()
        }

    fun getPosts() {
        viewModelScope.launch {
            setLoading(true)
            val favorites = repository.getFavorites()
            val deleted = repository.getDeleted()
            setItems(repository.getPosts(), favorites, deleted)
        }
    }

    fun getFavorites(): List<Favorite> =
        state.value?.favorites ?: mutableListOf()

    fun getDeletedItems(): List<DeletedItem> =
        state.value?.deleted ?: mutableListOf()

    private fun setItems(newItems: List<Post>, favorites: List<Favorite>, deleted: List<DeletedItem>){
        val newState = state.value?.copy(
            favorites = favorites,
            deleted = deleted
        ) ?: ListUIState(
            favorites = favorites,
            deleted = deleted
        )
        newState.apply {
            items.clear()
            items.addAll(newItems)
            isLoading = false
        }
        state.postValue(newState)
    }

    private fun setLoading(loading: Boolean) {
        state.postValue(
            state.value?.copy(isLoading = loading)
        )
    }

    fun restoreAll() {
        viewModelScope.launch {
            repository.restore()
            getPosts()
        }
    }

    fun removeAll(){
        viewModelScope.launch {
            state.value?.items?.let {
                repository.removeAll(it)
                getPosts()
            }
        }
    }

}