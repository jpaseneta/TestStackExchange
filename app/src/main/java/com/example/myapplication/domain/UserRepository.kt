package com.example.myapplication.domain

interface UserRepository {
    suspend fun getUsers(): List<User>
}