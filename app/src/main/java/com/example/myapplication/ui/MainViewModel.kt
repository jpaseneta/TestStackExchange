package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.User
import com.example.myapplication.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime.ofInstant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _allUsers = MutableStateFlow<List<User>>(emptyList())

    private val _filteredUsers = MutableStateFlow<List<User>>(emptyList())
    val filteredUsers: StateFlow<List<User>> = _filteredUsers.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        refresh()
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
        filterUsers()
    }

    fun filterUsers() {
        val query = _searchQuery.value
        _filteredUsers.update {
            if (query.isBlank()) {
                _allUsers.value
            } else {
                _allUsers.value.filter {
                    it.display_name.contains(query, ignoreCase = true)
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _error.value = null
            try {
                val users = userRepository.getUsers()
                _allUsers.value = users
                filterUsers()
            } catch (e: Exception) {
                _error.value = e.message ?: "An unexpected error occurred"
                e.printStackTrace()
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun getUserFromList(userId: Int?): User? = _allUsers.value.find { it.user_id == userId }


}
