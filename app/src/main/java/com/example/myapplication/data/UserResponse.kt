package com.example.myapplication.data

import com.example.myapplication.domain.BadgeCounts
import com.example.myapplication.domain.User
import com.example.myapplication.extensions.convertEpochToLocalDateTime
import java.time.Instant
import java.time.LocalDateTime.ofInstant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class UserListResponse(
    val items: List<UserDto>,
    val has_more: Boolean,
    val quota_max: Int,
    val quota_remaining: Int
)

data class UserDto(
    val user_id: Int,
    val display_name: String,
    val profile_image: String,
    val reputation: Int,
    val creation_date: Long,
    val badge_counts: BadgeCountsDto
)

data class BadgeCountsDto(
    val gold: Int,
    val silver: Int,
    val bronze: Int
)

fun UserDto.toDomain(): User = User(
    user_id = user_id,
    display_name = display_name,
    profile_image = profile_image,
    reputation = reputation,
    creation_date = creation_date.convertEpochToLocalDateTime(),
    badge_counts = BadgeCounts(
        gold = badge_counts.gold,
        silver = badge_counts.silver,
        bronze = badge_counts.bronze
    )
)
