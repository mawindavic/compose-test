package com.mawinda.domain.model

import android.content.Context
import androidx.annotation.StringRes

sealed interface StringValue {
    data class HardCodedString(
        val value: String,
    ) : StringValue

    data object Empty : StringValue

    class StringResource(
        @StringRes val resID: Int,
        vararg val args: Any,
    ) : StringValue

    fun asString(context: Context?): String = when (this) {
        is HardCodedString -> value
        Empty -> ""
        is StringResource -> context?.getString(resID, *args).orEmpty()
    }
}
