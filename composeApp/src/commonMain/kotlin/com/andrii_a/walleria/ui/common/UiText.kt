package com.andrii_a.walleria.ui.common

import androidx.compose.runtime.Composable
import com.andrii_a.walleria.ui.common.UiText.DynamicString
import com.andrii_a.walleria.ui.common.UiText.StringRes
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {
    data class DynamicString(
        val value: String
    ) : UiText

    data class StringRes(
        val id: StringResource,
        val args: List<Any> = emptyList()
    ) : UiText
}

fun UiText.asString(): String {
    return when (this) {
        is DynamicString -> value
        is StringRes -> runBlocking { getString(id, *args.toTypedArray()) }
    }
}

@Composable
fun UiText.asStringComposable(): String {
    return when (this) {
        is DynamicString -> value
        is StringRes -> stringResource(id, *args.toTypedArray())
    }
}
