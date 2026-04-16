package com.example.myapplication.data

import com.example.myapplication.domain.User
import com.example.myapplication.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val service: StackExchangeService
) : UserRepository {
    override suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        service.getUsers().items.map { it.toDomain() }
    }

}
