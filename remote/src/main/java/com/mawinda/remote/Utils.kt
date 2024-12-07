package com.mawinda.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import timber.log.Timber
import kotlin.reflect.full.memberProperties

object RemoteJsonUtil {

    /**
     * Get Items from Json String
     */
    @Throws
    inline fun <reified T : Any> getItems(body: String): List<T> {

        //Checks if T is data class
        require(T::class.isData) { "${T::class.simpleName} is not a data class" }

        //Get keys using reflection
        val keys = T::class.memberProperties.map {
            it.annotations.filterIsInstance<SerialName>().firstOrNull()?.value ?: it.name
        }

        // return empty list if body is empty
        if (body.isEmpty())
            return emptyList()

        val itemList: MutableList<T> = mutableListOf()
        val json = Json.parseToJsonElement(body)
        json.findJsonObjectWithKeys(keys = keys.toTypedArray()) { jsonObject ->
            if (jsonObject != null) {
                itemList.add(Json.decodeFromJsonElement(jsonObject))
            }

        }
        return itemList
    }

    /**
     * Parse Json Element to get target object
     * @param keys Array of keys to search for
     */
    fun JsonElement.findJsonObjectWithKeys(vararg keys: String, collect: (JsonObject?) -> Unit) {
        when (this) {
            is JsonObject -> {
                if (this.keys.containsAll(keys.asList())) {
                    collect.invoke(this)
                    return
                } else {
                    entries.onEach { (_, value) ->
                        value.findJsonObjectWithKeys(keys = keys, collect = collect)
                    }
                }
            }

            is JsonArray -> onEach {
                it.findJsonObjectWithKeys(keys = keys, collect = collect)
            }

            else -> collect.invoke(null).also {
                Timber.w("Unexpected JSON structure: $this")
            }
        }
    }

}

