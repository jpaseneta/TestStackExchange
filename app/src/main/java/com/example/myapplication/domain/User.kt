package com.example.myapplication.domain

data class BadgeCounts(
    val gold: Int = 0,
    val silver: Int = 0,
    val bronze: Int = 0
)

data class User(
    val user_id: Int,
    val display_name: String,
    val profile_image: String,
    val reputation: Int,
    val badge_counts: BadgeCounts = BadgeCounts()
)

val sampleUsers = listOf(
    User(
        1,
        "paradigmSh!t",
        "some url 1",
        100,
        BadgeCounts(1, 2, 5)
    ),
    User(2,
        "chancell0r",
        "some url 2",
        89,
        BadgeCounts(0, 1, 3)),
    User(
        3,
        "user3321",
        "some url 3",
        50,
        BadgeCounts(0, 0, 1)
    ),
    User(
        4,
        "theAmazingSpiderMan",
        "some url 4",
        29,
        BadgeCounts(2, 5, 10)
    ),
    User(5, "theHulk4312", "some url 5", 14, BadgeCounts(0, 0, 2)),
    User(
        6,
        "capt@inAmericA",
        "Some url 6",
        31,
        BadgeCounts(1, 1, 1)
    ),
    User(
        7,
        "IbongAdarna221",
        "some url 7",
        0,
        BadgeCounts(0, 0, 0)
    ),
    User(8, "TheAlchemist32", "some url 8", 98, BadgeCounts(0, 3, 7)),
    User(
        9,
        "PauloCoelho",
        "some url 9",
        22,
        BadgeCounts(0, 0, 5)
    ),
    User(
        10,
        "Aldous22",
        "some url 10",
        12,
        BadgeCounts(0, 0, 1)
    )
)