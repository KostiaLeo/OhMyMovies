package com.lyft.android.ohmymovies.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class StringObject(val value: String) : UiText()
    data class StringResource(@StringRes val resId: Int) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is StringObject -> value
            is StringResource -> stringResource(resId)
        }
    }
}
