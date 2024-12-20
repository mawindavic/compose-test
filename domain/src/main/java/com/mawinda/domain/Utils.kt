package com.mawinda.domain

import java.util.Calendar
import java.util.Locale

/**
 * Generates a user-friendly greeting based on the current time of day.
 * @param hourOfDay The current hour of the day (0-23).
 * @param username The user's name.
 */
fun generateUserTimeBasedGreetings(
    username: String = "",
    hourOfDay: Int = Calendar.getInstance(Locale.getDefault())[Calendar.HOUR_OF_DAY]
): String {
    val greeting = when (hourOfDay) {
        in 0..11 -> "Good Morning"
        in 12..15 -> "Good Afternoon"
        else -> "Good Evening"
    }

    return when (username.isBlank()) {
        true -> "$greeting!"
        false -> "$greeting, $username!"
    }
}


/**
 * Validates the username.
 */
fun String.isValidUsername(): Pair<Boolean, String> {
    return when (this.isBlank()) {
        true -> false to "Username cannot be empty"
        false -> true to ""
    }
}


/**
 * Validates the password.
 */
fun String.isValidPassword(): Pair<Boolean, String> {
    return when (this.isBlank()) {
        true -> false to "Password cannot be empty"
        false -> true to ""
    }
}


