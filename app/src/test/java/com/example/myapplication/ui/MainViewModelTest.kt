package com.example.myapplication.ui

import com.example.myapplication.domain.BadgeCounts
import com.example.myapplication.domain.User
import com.example.myapplication.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val userRepository: UserRepository = mock()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: MainViewModel

    private val mockUsers = listOf(
        User(1, "Alice", "url1", "2021-01-01", 100, BadgeCounts(1, 1, 1)),
        User(2, "Bob", "url2", "2021-01-02", 200, BadgeCounts(2, 2, 2))
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `refresh updates users on success`() = runTest {
        // Arrange
        whenever(userRepository.getUsers()).thenReturn(mockUsers)
        val expectedSuccessState = UIState.SuccessState(mockUsers)
        // Act
        viewModel = MainViewModel(userRepository)
        advanceUntilIdle()

        // Assert
        assertEquals(expectedSuccessState, viewModel.uiState.value)
    }

    @Test
    fun `refresh updates error on failure`() = runTest {
        // Arrange
        val errorMessage = "Network Error"
        val expectedErrorState = UIState.ErrorState(errorMessage)
        whenever(userRepository.getUsers()).thenThrow(RuntimeException(errorMessage))

        // Act
        viewModel = MainViewModel(userRepository)
        advanceUntilIdle()

        // Assert
        assertEquals(expectedErrorState, viewModel.uiState.value)
    }

    @Test
    fun `onSearchQueryChange filters users correctly`() = runTest {
        // Arrange
        whenever(userRepository.getUsers()).thenReturn(mockUsers)
        viewModel = MainViewModel(userRepository)

        // Act
        advanceUntilIdle()
        viewModel.onSearchQueryChange("Alice")
        val actualUIState = viewModel.uiState.value as UIState.SuccessState

        // Assert
        assertEquals(1, actualUIState.users.size)
        assertEquals("Alice", actualUIState.users[0].display_name)
    }

    @Test
    fun `onSearchQueryChange filters users case-insensitively`() = runTest {
        // Arrange
        whenever(userRepository.getUsers()).thenReturn(mockUsers)
        viewModel = MainViewModel(userRepository)
        advanceUntilIdle()

        // Act
        viewModel.onSearchQueryChange("bob")
        val actualUIState = viewModel.uiState.value as UIState.SuccessState

        // Assert
        assertEquals(1, actualUIState.users.size)
        assertEquals("Bob", actualUIState.users[0].display_name)
    }

    @Test
    fun `getUserFromList returns correct user`() = runTest {
        // Arrange
        whenever(userRepository.getUsers()).thenReturn(mockUsers)
        viewModel = MainViewModel(userRepository)
        advanceUntilIdle()

        // Act
        val result = viewModel.getUserFromList(2)

        // Assert
        assertEquals("Bob", result?.display_name)
    }
}
