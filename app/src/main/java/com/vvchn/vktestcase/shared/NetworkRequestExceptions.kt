package com.vvchn.vktestcase.shared

sealed class NetworkRequestExceptions : Throwable() {
    class NetworkError(val httpCode: Int) : NetworkRequestExceptions()
    class TimeOutError(): NetworkRequestExceptions()
    class ConnectionError() : NetworkRequestExceptions()
    class UnknownError() : NetworkRequestExceptions()
}