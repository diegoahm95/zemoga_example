package com.diegohurtado.zemoga.feature.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegohurtado.zemoga.core.model.entities.Post
import com.diegohurtado.zemoga.core.model.entities.User
import com.diegohurtado.zemoga.core.repository.post.PostRepository
import com.diegohurtado.zemoga.core.repository.user.UserRepository
import com.diegohurtado.zemoga.feature.detail.view.DetailsUIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel: ViewModel() {

    @Inject
    lateinit var repository: PostRepository
    @Inject
    lateinit var userRepository: UserRepository
    val state: MutableLiveData<DetailsUIState> =
        MutableLiveData<DetailsUIState>().apply {
            value = DetailsUIState(
                Post(),
                mutableListOf(),
                User(),
                isLoading = false,
                isFavorite = false,
                deleted = false
            )
        }

    fun setPost(post: Post) {
        state.value = state.value?.copy(post = post)
    }

    fun getExtraInfo() {
        viewModelScope.launch {
            setLoading(true)
            state.value?.let {
                val user = getUserInfo(it.post)
                val comments =  getComments(it.post)
                val newState = it.copy(
                    user = user,
                    comments = comments,
                    isLoading = false,
                    isFavorite = isFavorite(it.post)
                )
                state.postValue(newState)
            }
        }
    }

    private fun setLoading(isLoading: Boolean){
        state.postValue(state.value?.copy(isLoading = isLoading))
    }

    private suspend fun getUserInfo(post: Post): User =
        userRepository.getUserInfo(post.userId.toString())

    private suspend fun getComments(post: Post) =
        repository.getPostComments(post.id.toString())

    private suspend fun isFavorite(post: Post): Boolean =
        repository.isFavorite(post)

    fun toggleFavorite() {
        viewModelScope.launch {
            state.value?.post?.let {
                state.postValue(state.value?.copy(isFavorite = repository.toggleFavorite(it)))
            }
        }
    }

    fun deleteItem() {
        viewModelScope.launch {
            state.value?.post?.let {
                repository.deletePost(it)
                state.postValue(state.value?.copy(deleted = true))
            }
        }
    }

}