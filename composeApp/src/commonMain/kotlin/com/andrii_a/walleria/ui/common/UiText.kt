package com.andrii_a.walleria.ui.common

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {
    data class DynamicString(
        val value: String
    ) : UiText

    data class WStringResource(
        val id: StringResource,
        val args: List<Any> = emptyList()
    ) : UiText

    /*fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is WStringResource -> context.getString(id, *args.toTypedArray())
        }
    }*/

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is WStringResource -> stringResource(id, *args.toTypedArray())
        }
    }
}
