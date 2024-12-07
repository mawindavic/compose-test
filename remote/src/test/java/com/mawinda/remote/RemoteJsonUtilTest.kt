package com.mawinda.remote

import org.junit.Assert.assertThrows
import org.junit.Test

class RemoteJsonUtilTest {

    @Test
    fun getItems_ThrowIllegalArgumentException_WhenNotDataClass() {
        assertThrows(IllegalArgumentException::class.java) {
            RemoteJsonUtil.getItems<String>("")
        }
    }


}