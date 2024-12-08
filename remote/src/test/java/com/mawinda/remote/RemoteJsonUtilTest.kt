package com.mawinda.remote

import com.mawinda.remote.model.MedDTO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class RemoteJsonUtilTest {

    @Test
    fun getItems_ThrowIllegalArgumentException_WhenNotDataClass() {
        assertThrows(IllegalArgumentException::class.java) {
            RemoteJsonUtil.getItems<String>("")
        }
    }


    @Test
    fun getItems_ReturnsEmptyList_WhenJsonIsEmpty() {
        val items = RemoteJsonUtil.getItems<MedDTO>("")
        assert(items.isEmpty())
    }

    @Test
    fun getItems_ReturnsList4Items_onSampleJson() {
        val items = RemoteJsonUtil.getItems<MedDTO>(jsonData)
        assertEquals(items.count(), 4)
    }
}