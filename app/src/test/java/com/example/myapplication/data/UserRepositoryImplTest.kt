package com.example.myapplication.data

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UserRepositoryImplTest {

    private val service: StackExchangeService = mock()
    private val repository = UserRepositoryImpl(service)

    @Test
    fun `getUsers returns list of users mapped from service response`() = runTest {
        // Arrange
        val userDto = UserDto(
            user_id = 1,
            display_name = "Test User",
            profile_image = "https://example.com/image.png",
            reputation = 500,
            creation_date = 1609459200, // Friday, January 1, 2021
            badge_counts = BadgeCountsDto(gold = 1, silver = 2, bronze = 3)
        )
        val response = UserListResponse(
            items = listOf(userDto),
            has_more = false,
            quota_max = 300,
            quota_remaining = 299
        )

        // The interface has 4 parameters: site, order, sort, pageSize
        whenever(service.getUsers(any(), any(), any(), any())).thenReturn(response)

        // Act
        val result = repository.getUsers()

        // Assert
        assertEquals(1, result.size)
        val user = result[0]
        assertEquals(userDto.toDomain(), user)
        verify(service).getUsers(any(), any(), any(), any())
    }

    @Test
    fun `Return empty list when service returns no items`() = runTest {
        // Arrange
        val response = UserListResponse(
            items = emptyList(),
            has_more = false,
            quota_max = 300,
            quota_remaining = 299
        )
        whenever(service.getUsers(any(), any(), any(), any())).thenReturn(response)

        // Act
        val result = repository.getUsers()

        // Assert
        assertEquals(0, result.size)
        verify(service).getUsers(any(), any(), any(), any())
    }
}
