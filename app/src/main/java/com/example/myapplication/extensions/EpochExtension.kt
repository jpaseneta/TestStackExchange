package com.example.myapplication.extensions

import java.time.Instant
import java.time.LocalDateTime.ofInstant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun Long.convertEpochToLocalDateTime(): String {
    val localDateTime = ofInstant(
        Instant.ofEpochMilli(this),
        ZoneId.systemDefault()
    )
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return localDateTime.format(formatter)
}