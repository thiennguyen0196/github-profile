package co.thiennguyen.github_profile.ui

import android.content.Context
import co.thiennguyen.github_profile.R
import co.thiennguyen.github_profile.domain.exceptions.ApiException
import co.thiennguyen.github_profile.extensions.showToast

fun Throwable.userReadableMessage(context: Context): String {
    return when (this) {
        is ApiException -> error?.message
        else -> message
    } ?: context.getString(R.string.error_generic)
}

fun Throwable.showToast(context: Context) =
    context.showToast(userReadableMessage(context))
