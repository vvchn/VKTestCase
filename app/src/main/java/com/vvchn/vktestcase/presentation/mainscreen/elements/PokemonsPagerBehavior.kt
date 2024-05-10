package com.vvchn.vktestcase.presentation.mainscreen.elements

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.vvchn.vktestcase.domain.models.Pokemon

fun LazyPagingItems<Pokemon>.isErrorOccurredWhileAppending(): Boolean = loadState.append is LoadState.Error

fun LazyPagingItems<Pokemon>.isErrorOccurredWhileRefreshing(): Boolean = loadState.refresh is LoadState.Error

fun LazyPagingItems<Pokemon>.isEndOfPaginationReached(): Boolean {
    val loadStateAppend = loadState.append
    val loadStateRefresh = loadState.refresh
    return (loadStateAppend.endOfPaginationReached) || (loadStateRefresh.endOfPaginationReached)
}
