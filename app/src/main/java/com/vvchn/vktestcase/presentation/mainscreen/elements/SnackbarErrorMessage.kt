package com.vvchn.vktestcase.presentation.mainscreen.elements

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import com.vvchn.vktestcase.R
import com.vvchn.vktestcase.common.PagingExceptions

suspend fun SnackbarErrorMessage(
    context: Context,
    snackbarHostState: SnackbarHostState,
    error: Throwable
) {
    when(error) {
        is PagingExceptions.NetworkError -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.httpError)} ${error.httpCode}")
        }
        is PagingExceptions.TimeOutError -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.timed_out)}")
        }
        is PagingExceptions.ConnectionError -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.check_connection)}")
        }
        is PagingExceptions.UnknownError -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.unknown_error)}")
        }
        else -> {
            snackbarHostState.showSnackbar("${context.getString(R.string.unknown_error)}")
        }
    }
}