package com.example.myapplication.ui

import com.example.myapplication.domain.User

sealed class UIState(val isRefreshing: Boolean) {
    object LoadingState : UIState(true)
    data class SuccessState(val users: List<User>) : UIState(false)
    data class ErrorState(val message: String) : UIState(false)
}