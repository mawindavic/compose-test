package com.mawinda.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsKtTest {

    @Test
    fun generateUserTimeBasedGreetings_ReturnsGoodAfternoon_WhenHourOfDayIs13() {
        val userName = "Alice"
        val hourOfDay = 13
        val expectedGreeting =
            generateUserTimeBasedGreetings(userName, hourOfDay)

        assertEquals("Good Afternoon, Alice!", expectedGreeting)
    }

    @Test
    fun generateUserTimeBasedGreetings_ReturnsOnlyGreeting_WhenUsernameIsBlank() {
        val userName = ""
        val hourOfDay = 10
        val expectedGreeting = generateUserTimeBasedGreetings(userName, hourOfDay)

        assertEquals("Good Morning!", expectedGreeting)

    }
}