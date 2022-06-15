package com.diegohurtado.zemoga.core.repository.user.datasources

import com.diegohurtado.zemoga.core.api.ApiService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUser(userId: String) =
        apiService.getUser(userId)
}