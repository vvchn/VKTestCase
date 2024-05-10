package com.vvchn.vktestcase.common

sealed class PagingExceptions : Throwable() {
    class NetworkError(val httpCode: Int) : PagingExceptions()
    class TimeOutError(): PagingExceptions()
    class ConnectionError() : PagingExceptions()
    class UnknownError() : PagingExceptions()
}