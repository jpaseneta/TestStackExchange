package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.User
import com.example.myapplication.domain.sampleUsers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainViewModel : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _filteredUsers = MutableStateFlow(sampleUsers)
    val filteredUsers: StateFlow<List<User>> = _filteredUsers.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun filterBooks() {
        val query = _searchQuery.value
        _filteredUsers.update {
            if (query.isBlank()) {
                sampleUsers
            } else {
                sampleUsers.filter {
                    it.display_name.contains(query, ignoreCase = true)
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            // Simulate network/data refresh delay
            delay(1000)
            _searchQuery.value = ""
            _filteredUsers.value = sampleUsers
            _isRefreshing.value = false
        }
    }

    /**
     * Converts an epoch timestamp in milliseconds to a LocalDateTime using the system default timezone.
     */
    private fun convertEpochToLocalDateTime(epochMillis: Long): String {
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(epochMillis),
            ZoneId.systemDefault()
        )
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return localDateTime.format(formatter)
    }
}
