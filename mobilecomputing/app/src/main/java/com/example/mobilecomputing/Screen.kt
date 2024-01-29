package com.example.mobilecomputing

import androidx.annotation.StringRes

sealed class Screen (val route: String, @StringRes val resourceId: Int) {
    object Conversation : Screen("conversation", R.string.conversation)
    object Options: Screen("options", R.string.options)
}
