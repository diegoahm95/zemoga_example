package com.diegohurtado.zemoga.core.repository.user

import com.diegohurtado.zemoga.core.model.entities.User
import com.diegohurtado.zemoga.core.repository.user.datasources.UserRemoteDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
) {

    suspend fun getUserInfo(userId: String): User {
        return remoteDataSource.getUser(userId)
    }

}