package com.example.myapplication.domain

import com.example.myapplication.extensions.convertEpochToLocalDateTime

data class BadgeCounts(
    val gold: Int = 0,
    val silver: Int = 0,
    val bronze: Int = 0
)

data class User(
    val user_id: Int,
    val display_name: String,
    val profile_image: String,
    val creation_date: String,
    val reputation: Int,
    val badge_counts: BadgeCounts = BadgeCounts()
)

val sampleUsers = listOf(
    User(
        1,
        "paradigmSh!t",
        "some url 1",
        1341123603L.convertEpochToLocalDateTime(),
        100,
        BadgeCounts(1, 2, 5)
    ),
    User(
        2,
        "chancell0r",
        "some url 2",
        1341124633L.convertEpochToLocalDateTime(),
        89,
        BadgeCounts(0, 1, 3)
    ),
    User(
        3,
        "user3321",
        "some url 3",
        1341125603L.convertEpochToLocalDateTime(),
        50,
        BadgeCounts(0, 0, 1)
    ),
    User(
        4,
        "theAmazingSpiderMan",
        "some url 4",
        1341126603L.convertEpochToLocalDateTime(),
        29,
        BadgeCounts(2, 5, 10)
    ),
    User(
        5,
        "theHulk4312",
        "some url 5",
        135113603L.convertEpochToLocalDateTime(),
        14,
        BadgeCounts(0, 0, 2)
    ),
    User(
        6,
        "capt@inAmericA",
        "Some url 6",
        1341127603L.convertEpochToLocalDateTime(),
        31,
        BadgeCounts(1, 1, 1)
    ),
    User(
        7,
        "IbongAdarna221",
        "some url 7",
        134163603L.convertEpochToLocalDateTime(),
        0,
        BadgeCounts(0, 0, 0)
    ),
    User(8,
        "TheAlchemist32",
        "some url 8",
        1341423603L.convertEpochToLocalDateTime(),
        98,
        BadgeCounts(0, 3, 7)),
    User(
        9,
        "PauloCoelho",
        "some url 9",
        1341323603L.convertEpochToLocalDateTime(),
        22,
        BadgeCounts(0, 0, 5)
    ),
    User(
        10,
        "Aldous22",
        "some url 10",
        1341323603L.convertEpochToLocalDateTime(),
        12,
        BadgeCounts(0, 0, 1)
    )
)