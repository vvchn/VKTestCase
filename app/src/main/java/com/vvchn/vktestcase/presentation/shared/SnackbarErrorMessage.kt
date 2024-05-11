package com.vvchn.vktestcase.presentation.shared

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import com.vvchn.vktestcase.R
import com.vvchn.vktestcase.shared.NetworkRequestExceptions

suspend fun SnackbarErrorMessage(
    context: Context,
    snackbarHostState: SnackbarHostState,
    error: Throwable
) {
    when(error) {
        is NetworkRequestExceptions.NetworkError -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.httpError)} ${error.httpCode}")
        }
        is NetworkRequestExceptions.TimeOutError -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.timed_out)}")
        }
        is NetworkRequestExceptions.ConnectionError -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.check_connection)}")
        }
        is NetworkRequestExceptions.UnknownError -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.unknown_error)}")
        }
        else -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.unknown_error)}")
        }
    }
}