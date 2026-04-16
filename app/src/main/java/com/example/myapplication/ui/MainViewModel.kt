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
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    private val _allUsers = MutableStateFlow<List<User>>(emptyList())
    private val _uiState = MutableStateFlow<UIState>(UIState.LoadingState)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
        filterUsers()
    }

    private fun filterUsers() {
        val query = _searchQuery.value
        val userList = if (query.isBlank()) {
            _allUsers.value
        } else {
            _allUsers.value.filter {
                it.display_name.contains(query, ignoreCase = true)
            }
        }
        _uiState.update { UIState.SuccessState(userList) }
    }

    fun refresh() {
        viewModelScope.launch {
            _searchQuery.value = "" //resets search query
            _uiState.update { UIState.LoadingState }
            try {
                val users = userRepository.getUsers()
                _allUsers.value = users
                _uiState.update { UIState.SuccessState(users) }
            } catch (e: Exception) {
                _uiState.update { UIState.ErrorState(e.message ?: "An unexpected error occurred") }
                e.printStackTrace()
            }
        }
    }

    fun getUserFromList(userId: Int?): User? = _allUsers.value.find { it.user_id == userId }


}
